package com.example.moviemaster.data.remote.movies.mappers

import com.example.moviemaster.data.remote.movies.ApiMapper
import com.example.moviemaster.data.remote.movies.model.credits.ApiMovieCredits
import com.example.moviemaster.domain.movies.model.cast.Actor
import javax.inject.Inject

class ApiCreditsMapper @Inject constructor() : ApiMapper<ApiMovieCredits, List<Actor>> {
    override fun mapToDomain(input: ApiMovieCredits): List<Actor> {
        return input.cast?.map { actor ->
            Actor(
                actorsName = actor.name ?: "",
                character = actor.character ?: "",
                picture = actor.profilePath ?: ""
            )
        }?: listOf()
    }
}