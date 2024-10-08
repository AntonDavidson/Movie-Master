package com.example.moviemaster.data.repository

import com.example.moviemaster.data.local.dao.MovieDao
import com.example.moviemaster.data.local.entity.MovieEntity
import com.example.moviemaster.data.local.mapper.MovieDataBaseMapper
import com.example.moviemaster.data.remote.movies.api.MovieService
import com.example.moviemaster.data.remote.movies.mappers.ApiCreditsMapper
import com.example.moviemaster.data.remote.movies.mappers.BackdropApiMapper
import com.example.moviemaster.data.remote.movies.mappers.MovieApiMapper
import com.example.moviemaster.data.remote.movies.mappers.SingleMovieApiMapper
import com.example.moviemaster.domain.movies.model.cast.Actor
import com.example.moviemaster.domain.movies.model.images.Backdrop
import com.example.moviemaster.domain.movies.model.movies.MovieDetails
import com.example.moviemaster.domain.movies.model.movies.Movies
import com.example.moviemaster.domain.movies.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao,
    private val movieApiMapper: MovieApiMapper,
    private val backdropApiMapper: BackdropApiMapper,
    private val singleMovieApiMapper: SingleMovieApiMapper,
    private val apiCreditsMapper: ApiCreditsMapper,
    private val movieDataBaseMapper: MovieDataBaseMapper
) : MovieRepository {


    suspend fun getFavoritesMovies(): List<MovieDetails> {
        return movieDao.getAllMovies().map { movieDataBaseMapper.mapToDomain(it) }
    }

    suspend fun addMovieToFavorites(movieEntity: MovieEntity) {
        movieDao.addMovie(movieEntity)

    }

    suspend fun removeMovieFromFavorites(movieId: Long) {

        movieDao.deleteMovie(movieId)

    }

    suspend fun removeMovieAndUpdateFavorites(movieId: Long): List<MovieDetails> {
        return movieDao.removeAndUpdate(movieId).map { movieDataBaseMapper.mapToDomain(it) }
    }

    suspend fun getMovies(category: String, page: Int): Movies {
        return movieApiMapper.mapToDomain(movieService.getMovies(category = category, page = page))
    }

    suspend fun getActors(movieId: Long): List<Actor> {
        return apiCreditsMapper.mapToDomain(movieService.getMovieCredits(movieId))
    }

    suspend fun getSingleMovie(movieId: Long): MovieDetails {
        return singleMovieApiMapper.mapToDomain(movieService.getSingleMovieDetails(movieId))
    }

    suspend fun getMovieImages(movieId: Long): List<Backdrop> {
        return backdropApiMapper.mapToDomain(input = movieService.getMovieImages(movieId))
    }


    suspend fun checkFavorites(movieId: Long): Boolean {
        val favoritesMovie = movieDao.getMovieById(movieId)
        return favoritesMovie != null
    }
}