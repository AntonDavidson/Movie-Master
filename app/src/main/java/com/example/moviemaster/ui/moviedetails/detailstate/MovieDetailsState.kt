package com.example.moviemaster.ui.moviedetails.detailstate


import com.example.moviemaster.ui.moviedetails.model.ActorsDialogView
import com.example.moviemaster.ui.moviedetails.model.BackdropDialogView
import com.example.moviemaster.ui.moviedetails.model.MovieDetailsDialogView

data class MovieDetailsState(
    val movieDetails: MovieDetailsDialogView = MovieDetailsDialogView(),
    val backdropImages: BackdropDialogView = BackdropDialogView(),
    val movieCast: ActorsDialogView = ActorsDialogView()

    )