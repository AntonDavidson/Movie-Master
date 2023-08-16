package com.example.moviemaster.di.reposistory

import com.example.moviemaster.data.local.dao.MovieDao
import com.example.moviemaster.data.local.mapper.MovieDataBaseMapper
import com.example.moviemaster.data.remote.movies.api.MovieService
import com.example.moviemaster.data.remote.movies.mappers.ApiCreditsMapper
import com.example.moviemaster.data.remote.movies.mappers.BackdropApiMapper
import com.example.moviemaster.data.remote.movies.mappers.MovieApiMapper
import com.example.moviemaster.data.remote.movies.mappers.SingleMovieApiMapper
import com.example.moviemaster.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMovieRepo(
        movieService: MovieService,
        movieDao: MovieDao,
        movieApiMapper: MovieApiMapper,
        backdropApiMapper: BackdropApiMapper,
        singleMovieApiMapper: SingleMovieApiMapper,
        apiCreditsMapper: ApiCreditsMapper,
        movieDataBaseMapper: MovieDataBaseMapper
    ): MovieRepositoryImpl =
        MovieRepositoryImpl(
            movieService = movieService,
            movieDao = movieDao,
            movieApiMapper = movieApiMapper,
            backdropApiMapper = backdropApiMapper,
            singleMovieApiMapper = singleMovieApiMapper,
            apiCreditsMapper = apiCreditsMapper,
            movieDataBaseMapper = movieDataBaseMapper
        )

}