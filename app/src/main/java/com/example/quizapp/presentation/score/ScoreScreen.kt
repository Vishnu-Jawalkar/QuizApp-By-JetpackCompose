package com.example.quizapp.presentation.score

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizapp.R
import com.example.quizapp.presentation.navgraph.Routes
import com.example.quizapp.ui.Dimens
import java.text.DecimalFormat


@Preview
@Composable
fun Preview() {
  ScoreScreen(numOfQuestions = 10, numOfCorrectAnswers = 4,navController = rememberNavController())
}

@Composable
fun ScoreScreen(numOfQuestions: Int, numOfCorrectAnswers: Int,navController: NavController) {

    BackHandler {

        goToHome(navController)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            IconButton(
                onClick = {
                          //go to home screen
                          goToHome(navController = navController)

                },
            ){
                Icon(
                    painterResource(id = R.drawable.baseline_close_24),
                    contentDescription = "close",
                    tint = colorResource(id = R.color.blue_grey)
                )

            }

        }
        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                .background(colorResource(id = R.color.blue_grey)),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.SmallPadding, vertical = Dimens.MediumPadding),
                horizontalAlignment = Alignment.CenterHorizontally,)
            {

                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.congrats))
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)){
                        append("You Attempted!")
                    }
                    withStyle(style = SpanStyle(color = Color.Blue)){
                        append(" $numOfQuestions questions")
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(" and from that")
                    }
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.green)) ){
                        append("$numOfCorrectAnswers Answers")
                    }

                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(" are correctly")
                    }

                }

                val scorePercentage = calculatePercentage(numOfCorrectAnswers, numOfQuestions)
                LottieAnimation(
                    modifier = Modifier
                        .size(Dimens.LargeLottieAnimSize),
                    composition = composition,
                    iterations = 100
                )
                
                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                Text(
                    text = "Congrats!",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.MediumTextSize,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
                
                Text(text = "$scorePercentage% Score",
                    color = colorResource(id = R.color.green),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.LargeTextSize,
                    fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = "Quiz Completed successfully..",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.SmallTextSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = annotatedString,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.SmallTextSize,
                    textAlign =TextAlign.Center,

                )
                Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Share with your friends:",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = Dimens.SmallTextSize,

                    )

                }

            }
        }
    }
}

fun goToHome(navController: NavController){

    navController.navigate(Routes.HomeScreen.route){
        popUpTo(Routes.HomeScreen.route){inclusive = true}
    }
}

fun calculatePercentage(k: Int, n: Int): Double {
    require( k >= 0 && n > 0){
        "Invalid input: k must be non-negative and n must be positive "
    }
    val percentage = (k.toDouble() / n.toDouble()) * 100.0
    return DecimalFormat("#.##").format(percentage).toDouble()

}
