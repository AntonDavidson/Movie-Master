package com.example.moviemaster.domain.movies.model.movies


enum class MovieCategory(val displayName: String, val apiPath: String) {
    NOW_PLAYING(displayName = "Now Playing", apiPath = "now_playing"),
    UPCOMING("Upcoming", apiPath = "upcoming"),
    POPULAR("Popular", apiPath = "popular"),
    TOP_RATED("Top Rated", apiPath = "top_rated"),
    FAVORITES("Favorites", apiPath = "");

}