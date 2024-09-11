package com.example.moviemaster.ui.movie_details.model

data class MovieDetailsDialogView(
    val loading: Boolean=true,
    val genreIds: List<Long> = listOf(),
    val overview: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val voteAverage: Double = 5.0,
    val id: Long = 0L
)