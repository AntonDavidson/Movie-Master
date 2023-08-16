package com.example.moviemaster.ui.moviesstate

import com.example.moviemaster.ui.model.MoviesMainView

data class MoviesState(
    val loading: Boolean = true,
    val category: String = "",
    val movies: MoviesMainView = MoviesMainView()

)
