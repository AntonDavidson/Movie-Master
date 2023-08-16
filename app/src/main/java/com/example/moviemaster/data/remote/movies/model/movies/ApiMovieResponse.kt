package com.example.moviemaster.data.remote.movies.model.movies

import com.google.gson.annotations.SerializedName

data class ApiMovieResponse(
    @SerializedName("page")
    val page: Long?,
    @SerializedName("results")
    val results: List<ApiMovieDetails>?,
    @SerializedName("total_pages")
    val totalPages: Long?,
    @SerializedName("total_results")
    val totalResults: Long?
)