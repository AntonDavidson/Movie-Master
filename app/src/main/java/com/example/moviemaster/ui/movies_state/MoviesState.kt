package com.example.moviemaster.ui.movies_state

import com.example.moviemaster.ui.model.MoviesMainView

data class MoviesState(
    val loading: Boolean = true,
    val category: String = "",
    val movies: MoviesMainView = MoviesMainView()

)
