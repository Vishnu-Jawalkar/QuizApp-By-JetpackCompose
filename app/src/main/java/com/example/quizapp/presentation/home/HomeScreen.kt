package com.example.quizapp.presentation.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.quizapp.presentation.common.AppDropDownMenu
import com.example.quizapp.presentation.common.ButtonBox
import com.example.quizapp.presentation.navgraph.Routes
import com.example.quizapp.ui.Constants
import com.example.quizapp.ui.Constants.difficulty
import com.example.quizapp.ui.Constants.type
import com.example.quizapp.ui.Dimens
import com.example.quizapp.ui.Dimens.MediumSpacerHeight
import com.example.quizapp.ui.Dimens.SmallSpacerHeight


//@Preview
//@Composable
//fun PrevHome(){
//    HomeScreen(state = StateHomeScreen(),
//        event = {})
//}


@Composable
fun HomeScreen(
    state : StateHomeScreen,
    event : (EventHomeScreen) -> Unit,
    navController: NavController

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ){

        HomeHeader()
        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        AppDropDownMenu(menuName = "Numbers of Questions:", menuList = Constants.numbersAsString, text = state.numberOfQuiz.toString(), onDropDownClick = {event(EventHomeScreen.SetNumberOfQuizzes(it.toInt()))})

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(menuName = "Select Category:", menuList = Constants.categories, text = state.category, onDropDownClick = {event(EventHomeScreen.SetQuizCategory(it))})

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(menuName = "Select Difficulty:", menuList = difficulty, text = state.difficulty, onDropDownClick = {event(EventHomeScreen.SetQuizDifficulty(it))})

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(menuName = "Select Type:", menuList = type, text = state.type, onDropDownClick = {event(EventHomeScreen.SetQuizType(it))})

        Spacer(modifier = Modifier.height(MediumSpacerHeight))

        ButtonBox(text = "Generate Quiz", padding = Dimens.MediumPadding){

            navController.navigate(
                route = Routes.QuizScreen.passQuizParams(state.numberOfQuiz,state.category,state.difficulty,state.type)
            )

        }



    }

        
}