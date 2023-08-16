package com.example.moviemaster.data.remote.movies.model.movie

import com.google.gson.annotations.SerializedName

data class ApiSingleMovieCollection (
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("backdrop_path") val backdropPath: String?
    )
