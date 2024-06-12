package com.example.quizapp.presentation.home

sealed class EventHomeScreen {

    data class SetNumberOfQuizzes(val numberOfQuizzes: Int) : EventHomeScreen()
    data class SetQuizCategory(val quizCategory: String) : EventHomeScreen()
    data class SetQuizDifficulty(val quizDifficulty: String) : EventHomeScreen()
    data class SetQuizType(val quizType: String) : EventHomeScreen()


}