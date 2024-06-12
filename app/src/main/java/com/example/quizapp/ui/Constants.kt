package com.example.quizapp.ui

object Constants {

    val difficulty = listOf("Easy", "Medium", "Hard")
    val type = listOf("Multiple Choice", "TrueORFalse")

    val numbersAsString = listOf("10") + (1..50).map { it.toString() }
    val categories = listOf(

        "General Knowledge",
        "Entertainment: Books",
        "Entertainment: Film",
        "Entertainment: Music",
        "Entertainment: Television",
        "Entertainment: Video Games",
        "Entertainment: Musicals & Theatres",
        "Science & Nature",
        "Science: Computers",
        "Science: Mathematics",
        "Mythology",
        "Sports",
        "Geography",
        "History",
        "Politics",
        "Arts",
        "Celebrities",
        "Animals",
        "Vehicles",
        "Entertainment: Comics",
        "Science: Gadgets",
        "Entertainment: Japanese Anime & Manga",
        "Entertainment: Cartoon & Animations"
    )

    val categoriesMap  = mapOf(
        "General Knowledge" to 9,
        "Entertainment: Books" to 10,
        "Entertainment: Film" to 11,
        "Entertainment: Music" to 12,
        "Entertainment: Television" to 13,
        "Entertainment: Video Games" to 14,
        "Entertainment: Musicals & Theatres" to 15,
        "Science & Nature" to 16,
        "Science: Computers" to 17,
        "Science: Mathematics" to 18,
        "Mythology" to 19,
        "Sports" to 20,
        "Geography" to 21,
        "History" to 22,
        "Politics" to 23,
        "Arts" to 24,
        "Celebrities" to 25,
        "Animals" to 26,
        "Vehicles" to 27,
        "Entertainment: Comics" to 28,
        "Science: Gadgets" to 29,
        "Entertainment: Japanese Anime & Manga" to 30,
        "Entertainment: Cartoon & Animations" to 31,
    )


}