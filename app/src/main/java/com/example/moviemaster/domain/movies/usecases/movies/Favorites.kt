package com.example.moviemaster.domain.movies.usecases.movies

import com.example.moviemaster.data.repository.MovieRepositoryImpl
import com.example.moviemaster.domain.movies.mapper.DomainMovieMapper
import com.example.moviemaster.domain.movies.model.movies.Movies
import com.example.moviemaster.ui.mapper.MainViewMapper
import com.example.moviemaster.ui.model.MovieDetailsMainView
import com.example.moviemaster.ui.model.MoviesMainView
import javax.inject.Inject


class Favorites @Inject constructor(
    private val movieRepositoryImpl: MovieRepositoryImpl,
    private val domainMovieMapper: DomainMovieMapper,
    private val mainViewMapper: MainViewMapper
) {

    suspend fun getMovies(): MoviesMainView {
        return mainViewMapper.mapToView(
            Movies(results = movieRepositoryImpl.getFavoritesMovies())
        )
    }

    suspend fun addMovie(movieDetailsMainView: MovieDetailsMainView) {
        movieRepositoryImpl.addMovieToFavorites(domainMovieMapper.mapToDataBase(movieDetailsMainView))
    }

    suspend fun removeMovie(movieId: Long) {
        movieRepositoryImpl.removeMovieFromFavorites(movieId = movieId)
    }

}

