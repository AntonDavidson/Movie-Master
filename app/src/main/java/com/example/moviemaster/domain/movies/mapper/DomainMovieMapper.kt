package com.example.moviemaster.domain.movies.mapper

import com.example.moviemaster.data.local.entity.MovieEntity
import com.example.moviemaster.domain.DomainMapper
import com.example.moviemaster.ui.model.MovieDetailsMainView
import javax.inject.Inject

class DomainMovieMapper @Inject constructor() : DomainMapper<MovieDetailsMainView, MovieEntity> {

    override fun mapToDataBase(input: MovieDetailsMainView): MovieEntity {
        return MovieEntity(
            favorite = input.favorite,
            posterPath = input.posterPath,
            id = input.id
        )
    }
}