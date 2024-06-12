package com.example.quizapp.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenViewModel : ViewModel(){

    private val _homeState = MutableStateFlow(StateHomeScreen())
    val homeState = _homeState


    fun onEvent(event: EventHomeScreen){

        when(event){
            is EventHomeScreen.SetNumberOfQuizzes ->{

                _homeState.value = _homeState.value.copy(numberOfQuiz = event.numberOfQuizzes)
            }

            is EventHomeScreen.SetQuizCategory ->{

                _homeState.value = _homeState.value.copy(category = event.quizCategory)
            }
            is EventHomeScreen.SetQuizDifficulty ->{

                _homeState.value = _homeState.value.copy(difficulty = event.quizDifficulty)

            }
            is EventHomeScreen.SetQuizType ->{

                _homeState.value = _homeState.value.copy(type = event.quizType)

            }
            else ->{

            }
        }
    }
}