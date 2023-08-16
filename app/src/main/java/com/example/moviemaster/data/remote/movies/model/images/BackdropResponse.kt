package com.example.moviemaster.data.remote.movies.model.images

import com.google.gson.annotations.SerializedName

data class BackdropResponse(
    @SerializedName("backdrops") val backdrops: List<Backdrop>?
)
