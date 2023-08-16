package com.example.moviemaster.data.remote.movies.model.movie

import com.google.gson.annotations.SerializedName

data class ApiSingleMovieProductionCompany(
    @SerializedName("id") val id: Int?,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("origin_country") val originCountry: String?
)
