package com.example.moviemaster.domain.movies.model.movies

data class MovieDetails(
    val genreIds: List<Long> = listOf(),
    val overview: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val voteAverage: Double = 0.0,
    val id: Long = 0L
)
