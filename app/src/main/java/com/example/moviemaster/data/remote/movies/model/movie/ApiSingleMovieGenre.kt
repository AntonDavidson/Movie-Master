package com.example.moviemaster.data.remote.movies.model.movie

import com.google.gson.annotations.SerializedName

data class ApiSingleMovieGenre(
@SerializedName("id") val id: Int?,
@SerializedName("name") val name: String?
)
