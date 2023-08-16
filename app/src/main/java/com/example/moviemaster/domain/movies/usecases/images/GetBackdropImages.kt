package com.example.moviemaster.domain.movies.usecases.images

import android.util.Log
import com.example.moviemaster.data.repository.MovieRepositoryImpl
import com.example.moviemaster.domain.movies.model.images.Backdrop
import javax.inject.Inject

const val TAG = "GetBackdrop"

class GetBackdropImages @Inject constructor(private val movieRepository: MovieRepositoryImpl) {

    suspend fun fetch(movieId: Long): Result<List<Backdrop>> {
        return try {
            val backdropImages = movieRepository.getMovieImages(movieId)
            Log.i(TAG, "Result: Success")

            Result.success(backdropImages)

        } catch (e: Exception) {
            Log.i(TAG, "Result: Failure")

            Result.failure(e)
        }
    }
}