package com.example.moviemaster.data.remote.movies.mappers

import com.example.moviemaster.data.remote.movies.ApiMapper
import com.example.moviemaster.data.remote.movies.model.movie.ApiSingleMovieDetails
import com.example.moviemaster.domain.movies.model.movies.MovieDetails
import javax.inject.Inject

class SingleMovieApiMapper @Inject constructor(): ApiMapper<ApiSingleMovieDetails, MovieDetails> {
    override fun mapToDomain(input: ApiSingleMovieDetails): MovieDetails {
        return MovieDetails(
            genreIds = input.genres?.map { it.id?.toLong() ?: 0 } ?: emptyList(),
            overview = input.overview ?: "",
            posterPath = input.posterPath ?: "",
            releaseDate = input.releaseDate ?: "",
            title = input.title ?: "",
            voteAverage = input.voteAverage ?: 0.0,
            id = input.id?.toLong() ?: 0L
        )
    }
}