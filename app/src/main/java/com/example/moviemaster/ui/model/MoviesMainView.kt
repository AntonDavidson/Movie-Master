package com.example.moviemaster.ui.model

data class MoviesMainView(
    val category: String = "",
    val page: Int = 1,
    val totalPages: Int = 1,
    val movieDetailsMainView: List<MovieDetailsMainView> = listOf()
)