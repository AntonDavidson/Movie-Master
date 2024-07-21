package com.example.moviemaster.data.remote.movies.api


import com.example.moviemaster.BuildConfig
import com.example.moviemaster.data.remote.movies.model.credits.ApiMovieCredits
import com.example.moviemaster.data.remote.movies.model.images.BackdropResponse
import com.example.moviemaster.data.remote.movies.model.movie.ApiSingleMovieDetails
import com.example.moviemaster.data.remote.movies.model.movies.ApiMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/{category}")
    suspend fun getMovies(
        @Path("category") category: String = "now_playing",
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "en-US",
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): ApiMovieResponse

    @GET("/3/movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") movieId: Long,
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): BackdropResponse


    @GET("/3/movie/{movie_id}")
    suspend fun getSingleMovieDetails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): ApiSingleMovieDetails
    
    @GET("/3/movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Long,
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): ApiMovieCredits


}
