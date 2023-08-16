package com.example.moviemaster.domain.movies.usecases.movies

import android.util.Log
import com.example.moviemaster.data.repository.MovieRepositoryImpl
import com.example.moviemaster.ui.moviedetails.mapper.DialogViewMapper
import com.example.moviemaster.ui.moviedetails.model.MovieDetailsDialogView
import javax.inject.Inject

private const val TAG = "GetSingleMovie"

class GetSingleMovie @Inject constructor(
    private val movieRepositoryImpl: MovieRepositoryImpl,
    private val dialogViewMapper: DialogViewMapper
) {
    suspend fun fetch(movieId: Long): Result<MovieDetailsDialogView> {
        return try {
            val movie = movieRepositoryImpl.getSingleMovie(movieId)
            Log.i(TAG, "Result: Success")
            Result.success(dialogViewMapper.mapToView(movie))
        } catch (e: Exception) {
            Log.i(TAG, "Result: Failure")
            Result.failure(e)
        }
    }
}