package com.example.moviemaster.ui.moviedetails.mapper

import com.example.moviemaster.domain.movies.model.movies.MovieDetails
import com.example.moviemaster.ui.ViewMapper
import com.example.moviemaster.ui.moviedetails.model.MovieDetailsDialogView
import javax.inject.Inject

class DialogViewMapper @Inject constructor(): ViewMapper<MovieDetails, MovieDetailsDialogView> {
    override suspend fun mapToView(input: MovieDetails): MovieDetailsDialogView {
        return MovieDetailsDialogView(
            loading = true,
            genreIds = input.genreIds,
            overview = input.overview,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            title = input.title,
            voteAverage = input.voteAverage,
            id = input.id
        )
    }
}