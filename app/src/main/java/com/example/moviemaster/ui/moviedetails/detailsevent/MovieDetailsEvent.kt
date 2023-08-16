package com.example.moviemaster.ui.moviedetails.detailsevent

sealed class MovieDetailsEvent {
    class OnCreateDialog(val movieId: Long) : MovieDetailsEvent()
}