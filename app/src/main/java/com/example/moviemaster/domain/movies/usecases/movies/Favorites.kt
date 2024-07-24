package com.example.moviemaster.domain.movies.usecases.movies

import com.example.moviemaster.data.repository.MovieRepositoryImpl
import com.example.moviemaster.domain.movies.mapper.DomainMovieMapper
import com.example.moviemaster.domain.movies.model.movies.Movies
import com.example.moviemaster.ui.mapper.MainViewMapper
import com.example.moviemaster.ui.model.MovieDetailsMainView
import com.example.moviemaster.ui.model.MoviesMainView
import javax.inject.Inject


class Favorites @Inject constructor(
    private val movieRepository: MovieRepositoryImpl,
    private val domainMovieMapper: DomainMovieMapper,
    private val mainViewMapper: MainViewMapper
) {

    suspend fun getMovies(): MoviesMainView {
        return mainViewMapper.mapToView(
            Movies(results = movieRepository.getFavoritesMovies())
        )
    }

    suspend fun addMovie(movieDetailsMainView: MovieDetailsMainView) {
        movieRepository.addMovieToFavorites(domainMovieMapper.mapToDataBase(movieDetailsMainView))
    }

    suspend fun removeMovie(movieId: Long) {
        movieRepository.removeMovieFromFavorites(movieId = movieId)
    }

    suspend fun removeMovieAndUpdateFavorites(movieId: Long): MoviesMainView {
        return mainViewMapper.mapToView(
            Movies(
                results = movieRepository.removeMovieAndUpdateFavorites(
                    movieId
                )
            )
        )
    }
}

