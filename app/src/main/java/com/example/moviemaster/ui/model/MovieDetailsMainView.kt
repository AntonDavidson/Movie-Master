package com.example.moviemaster.ui.model

data class MovieDetailsMainView(
    var favorite: Boolean=false,
    val posterPath: String = "",
    val id: Long = 0L
)