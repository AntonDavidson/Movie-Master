package com.example.moviemaster.domain.movies.usecases.cast

import android.util.Log
import com.example.moviemaster.data.repository.MovieRepositoryImpl
import com.example.moviemaster.ui.moviedetails.mapper.ActorViewMapper
import com.example.moviemaster.ui.moviedetails.model.ActorsDialogView
import javax.inject.Inject
private const val TAG="GetActors"
class GetActors @Inject constructor(
    private val repository: MovieRepositoryImpl, private val actorViewMapper: ActorViewMapper
) {

    suspend fun fetch(movieId: Long): Result<ActorsDialogView> {
        return try {
            val cast = repository.getActors(movieId)
            Log.i(TAG, "Result: Success ")
            Result.success(actorViewMapper.mapToView(cast))
        } catch (e: Exception) {
            Log.i(TAG, "Result: Failure")
            Result.failure(e)
        }
    }
}