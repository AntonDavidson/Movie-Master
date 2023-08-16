package com.example.moviemaster.domain.movies.usecases.movies

import android.util.Log
import com.example.moviemaster.data.repository.MovieRepositoryImpl
import com.example.moviemaster.ui.mapper.MainViewMapper
import com.example.moviemaster.ui.model.MoviesMainView
import javax.inject.Inject

private const val TAG = "GetMainMovies"

class GetMovies @Inject constructor(
    private val movieRepositoryImpl: MovieRepositoryImpl,
    private val mainViewMapper: MainViewMapper
) {

    suspend fun fetch(category: String, page: Int): Result<MoviesMainView> {
        return try {
            val movies = movieRepositoryImpl.getMovies(category = category, page = page)
            Log.i(TAG, "Result Success: ${movies.results} ")
            Result.success(mainViewMapper.mapToView(movies)
            )

        } catch (e: Exception) {
            Log.i(TAG, "Result Failure")
            Result.failure(e)
        }

    }
}