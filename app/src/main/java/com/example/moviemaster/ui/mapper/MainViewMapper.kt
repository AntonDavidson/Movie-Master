package com.example.moviemaster.ui.mapper

import com.example.moviemaster.data.repository.MovieRepositoryImpl
import com.example.moviemaster.domain.movies.model.movies.Movies
import com.example.moviemaster.ui.ViewMapper
import com.example.moviemaster.ui.model.MovieDetailsMainView
import com.example.moviemaster.ui.model.MoviesMainView
import javax.inject.Inject

class MainViewMapper @Inject constructor(private val favoritesCheck: MovieRepositoryImpl) :
    ViewMapper<Movies, MoviesMainView> {
    override suspend fun mapToView(input: Movies): MoviesMainView {
        return MoviesMainView(
            page = input.page.toInt(),
            totalPages = input.totalPages.toInt(),
            movieDetailsMainView = input.results.map { movieDetails ->
                MovieDetailsMainView(
                    favorite =
                    favoritesCheck.checkFavorites(
                        movieId = movieDetails.id
                    ),
                    posterPath = movieDetails.posterPath,
                    id = movieDetails.id
                )
            }
        )
    }
}