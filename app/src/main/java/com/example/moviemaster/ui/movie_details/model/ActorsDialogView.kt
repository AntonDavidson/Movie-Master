package com.example.moviemaster.ui.movie_details.model

import com.example.moviemaster.domain.movies.model.cast.Actor

data class ActorsDialogView(val loading: Boolean = true, val actors: List<Actor> = listOf())
