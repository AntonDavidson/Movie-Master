package com.example.moviemaster.data.remote.movies.model.credits

import com.google.gson.annotations.SerializedName


data class ApiMovieCredits(
    @SerializedName("id") val id: Int?,
    @SerializedName("cast") val cast: List<ApiMovieCreditsCast>?
)