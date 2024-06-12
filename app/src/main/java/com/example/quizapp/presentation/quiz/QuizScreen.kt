package com.example.quizapp.presentation.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.quizapp.R
import com.example.quizapp.presentation.common.ButtonBox
import com.example.quizapp.presentation.common.QuizAppBar
import com.example.quizapp.presentation.navgraph.Routes
import com.example.quizapp.presentation.quiz.component.QuizInterface
import com.example.quizapp.presentation.quiz.component.ShimmerEffectQuizInterface
import com.example.quizapp.ui.Constants
import com.example.quizapp.ui.Dimens
import com.example.quizapp.ui.Dimens.LargeSpacerHeight
import com.example.quizapp.ui.Dimens.MediumCornerRadius
import com.example.quizapp.ui.Dimens.MediumPadding
import kotlinx.coroutines.launch

//@Preview
//@Composable
//fun Prevquiz()
//{
//    QuizScreen(
//        numOfQuiz = 12 ,
//        quizCategory = "G.K",
//        quizDifficulty = "Easy",
//        quizType = "Multiple Choice",
//        event = {},
//        state = StateQuizScreen()
//    )
//}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(

    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType: String,
    event : (EventQuizScreen) -> Unit,
    state : StateQuizScreen,
    navController: NavController
){

    BackHandler {
        navController.navigate(Routes.HomeScreen.route){
            popUpTo(Routes.HomeScreen.route){
                inclusive = true
            }
        }
    }
    LaunchedEffect(key1 = Unit){
        val difficulty = when(quizDifficulty){
             "Medium" -> "medium"
             "Easy" -> "easy"
             "Hard" -> "hard"
            else -> "easy"
        }
        val type = when(quizType){
            "Multiple Choice" -> "multiple"
            else -> "boolean"
        }
        event(EventQuizScreen.GetQuizzes(numOfQuiz, Constants.categoriesMap[quizCategory]!!, difficulty, type))
    }
    Column(
        modifier = Modifier
            .fillMaxSize())
    {

        QuizAppBar(quizCategory = quizCategory){

            navController.navigate(Routes.HomeScreen.route){
                popUpTo(Routes.QuizScreen.route){
                    inclusive = true
                }
            }
        }

    }

    Column(
        modifier = Modifier
            .padding(Dimens.SmallPadding)
            .fillMaxSize()
    ){

        Spacer(modifier = Modifier.height(LargeSpacerHeight))
        Spacer(modifier = Modifier.height(LargeSpacerHeight))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = "Questions: $numOfQuiz",
                color = colorResource(id = R.color.blue_grey)
            )

            Text(text = quizDifficulty,
                color = colorResource(id = R.color.blue_grey))

        }

        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.VerySmallViewHeight)
                .clip(RoundedCornerShape(MediumCornerRadius))
                .background(colorResource(id = R.color.blue_grey)),
        )
        Spacer(modifier = Modifier.height(LargeSpacerHeight))

        //Quiz Interface

        if (quizFetched(state)){

            val pagerState = rememberPagerState {
                state.quizState.size
            }
            
            HorizontalPager(state = pagerState) {index ->

                QuizInterface(
                    modifier = Modifier.weight(1f),
                    quizState = state.quizState[index],
                    onOptionSelected = {selectedIndex ->
                                       event(EventQuizScreen.SetOptionSelected(index, selectedIndex))
                                       },
                    qNumber = index + 1
                )
            }



            val buttonText by remember {
                derivedStateOf {
                    when (pagerState.currentPage) {
                        0 -> {
                            listOf("", "Next")

                        }

                        state.quizState.size - 1 -> {
                            listOf("Previous", "Submit")
                        }

                        else -> {
                            listOf("Previous", "Next")
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MediumPadding)
                    .navigationBarsPadding()
            )
            {

                val scope = rememberCoroutineScope()
                if(buttonText[0].isNotEmpty()) {
                    ButtonBox(
                        text = "Previous",
                        padding = Dimens.SmallPadding,
                        fraction = 0.43f,
                        fontSize = Dimens.SmallTextSize
                    )
                    {

                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }

                    }

                }else{
                    ButtonBox(
                        text = " ",
                        borderColor = colorResource(id = R.color.mid_slate_blue),
                        containerColor = colorResource(id =R.color.mid_slate_blue ),
                        fraction = 0.43f,
                        fontSize = Dimens.SmallTextSize
                    )
                    {
                        
                    }
                }



                ButtonBox(
                    text = buttonText[1],
                    padding =Dimens.SmallPadding,
                    borderColor = colorResource(id =R.color.white ),
                    containerColor =
                    if(pagerState.currentPage == state.quizState.size-1)colorResource(id = R.color.orange)
                    else colorResource(id = R.color.dark_slate_blue),

                    fraction = 1f,
                    textColor = colorResource(id = R.color.white),
                    fontSize = Dimens.SmallTextSize)
                {
                    if (pagerState.currentPage == state.quizState.size - 1) {
                        //TODO
                        //navigate to score screen
                        navController.navigate(Routes.ScoreScreen.passNumOfQuestionsAndCorrectAns(state.quizState.size,state.score))


                    }
                    else{
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage +1) }

                    }

                }



            }


        }

    }




}

@Composable
fun quizFetched(state: StateQuizScreen): Boolean {


    return when {
        state.isLoading -> {
            ShimmerEffectQuizInterface()
            false
        }

        state.quizState?.isNotEmpty() == true -> {
            true
        }

        else -> {
            Text(text = state.error.toString(), color =  colorResource(id = R.color.white))
            false
        }
    }
}



