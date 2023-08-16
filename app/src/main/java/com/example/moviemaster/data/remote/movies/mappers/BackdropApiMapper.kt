package com.example.moviemaster.data.remote.movies.mappers

import com.example.moviemaster.data.remote.movies.ApiMapper
import com.example.moviemaster.data.remote.movies.model.images.BackdropResponse
import com.example.moviemaster.domain.movies.model.images.Backdrop
import javax.inject.Inject

class BackdropApiMapper @Inject constructor() : ApiMapper<BackdropResponse, List<Backdrop>> {

    override fun mapToDomain(input: BackdropResponse): List<Backdrop> {
        return input.backdrops?.map { backdrop ->
            Backdrop(backdrop.filePath ?: "")
        } ?: emptyList()
    }
}