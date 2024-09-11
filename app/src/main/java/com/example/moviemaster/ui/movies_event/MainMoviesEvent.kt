package com.example.moviemaster.ui.movies_event

import com.example.moviemaster.ui.model.MovieDetailsMainView

sealed class MainMoviesEvent() {

    data class GetMainMovies(val category: String) : MainMoviesEvent()

    data object GetFavoriteMainMovies : MainMoviesEvent()

    data object NextPage : MainMoviesEvent()

    data object PreviousPage : MainMoviesEvent()

    data class AddToFavorites(val movieDetailsMainView: MovieDetailsMainView) : MainMoviesEvent()

    data class RemoveFromFavorites(val movieId: Long) : MainMoviesEvent()

}


