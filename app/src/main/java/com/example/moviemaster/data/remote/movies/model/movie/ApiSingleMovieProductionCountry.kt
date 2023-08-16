package com.example.moviemaster.data.remote.movies.model.movie

import com.google.gson.annotations.SerializedName


data class ApiSingleMovieProductionCountry(
    @SerializedName("iso_3166_1") val iso31661: String?,
    @SerializedName("name") val name: String?
)