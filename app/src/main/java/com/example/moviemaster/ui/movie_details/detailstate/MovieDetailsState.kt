package com.example.moviemaster.ui.movie_details.detailstate


import com.example.moviemaster.ui.movie_details.model.ActorsDialogView
import com.example.moviemaster.ui.movie_details.model.BackdropDialogView
import com.example.moviemaster.ui.movie_details.model.MovieDetailsDialogView

data class MovieDetailsState(
    val movieDetails: MovieDetailsDialogView = MovieDetailsDialogView(),
    val backdropImages: BackdropDialogView = BackdropDialogView(),
    val movieCast: ActorsDialogView = ActorsDialogView()

    )