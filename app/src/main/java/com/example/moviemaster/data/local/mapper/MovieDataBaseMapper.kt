package com.example.moviemaster.data.local.mapper

import com.example.moviemaster.data.local.DataBaseMapper
import com.example.moviemaster.data.local.entity.MovieEntity
import com.example.moviemaster.domain.movies.model.movies.MovieDetails
import javax.inject.Inject

class MovieDataBaseMapper @Inject constructor() : DataBaseMapper<MovieEntity, MovieDetails> {

    override fun mapToDomain(input: MovieEntity): MovieDetails {
        return MovieDetails(
            genreIds = listOf(),
            overview = "",
            posterPath = input.posterPath,
            releaseDate = "",
            title = "",
            voteAverage = 0.0,
            id = input.id
        )
    }

}