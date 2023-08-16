package com.example.moviemaster.domain.movies.model.movies


data class Movies(
    val page: Long = 1,
    val results: List<MovieDetails> = emptyList(),
    val totalPages: Long = 1,
    val totalResults: Long = 1
)