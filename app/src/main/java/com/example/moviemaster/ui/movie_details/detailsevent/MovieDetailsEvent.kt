package com.example.moviemaster.ui.movie_details.detailsevent

sealed class MovieDetailsEvent {
    class OnCreateDialog(val movieId: Long) : MovieDetailsEvent()
}