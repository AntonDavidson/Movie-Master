package com.example.moviemaster.data.remote.movies.mappers

import com.example.moviemaster.data.remote.movies.ApiMapper
import com.example.moviemaster.data.remote.movies.model.movies.ApiMovieResponse
import com.example.moviemaster.domain.movies.model.movies.Movies
import com.example.moviemaster.domain.movies.model.movies.MovieDetails
import javax.inject.Inject

class MovieApiMapper @Inject constructor() : ApiMapper<ApiMovieResponse, Movies> {
    override fun mapToDomain(input: ApiMovieResponse): Movies {
        return Movies(
            page = input.page ?: 0,
            results = input.results?.map { movie ->
                MovieDetails(
                    genreIds = movie.genreIds ?: emptyList(),
                    overview = movie.overview ?: "",
                    posterPath = movie.posterPath ?: "",
                    releaseDate = movie.releaseDate ?: "",
                    title = movie.title ?: "",
                    voteAverage = movie.voteAverage ?: 0.0,
                    id = movie.id ?: 0
                )
            } ?: emptyList(),
            totalPages = input.totalPages ?: 0,
            totalResults = input.totalResults ?: 0
        )
    }
}