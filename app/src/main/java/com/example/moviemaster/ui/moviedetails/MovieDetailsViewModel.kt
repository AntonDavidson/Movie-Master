package com.example.moviemaster.ui.moviedetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemaster.domain.movies.usecases.cast.GetActors
import com.example.moviemaster.domain.movies.usecases.images.GetBackdropImages
import com.example.moviemaster.domain.movies.usecases.movies.GetSingleMovie
import com.example.moviemaster.ui.moviedetails.detailsevent.MovieDetailsEvent
import com.example.moviemaster.ui.moviedetails.detailstate.MovieDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MovieDetailsViewModel"

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getActors: GetActors,
    private val getBackdropImages: GetBackdropImages,
    private val getSingleMovie: GetSingleMovie
) :
    ViewModel() {
    private val movieDetailsState = MutableStateFlow(MovieDetailsState())
    val movieDetails: StateFlow<MovieDetailsState> = movieDetailsState


    fun event(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.OnCreateDialog -> {
                getMovieDetails(event.movieId)
                getBackdropImages(event.movieId)
                getMovieCast(event.movieId)
            }
        }
    }


    private fun getMovieDetails(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetailsState.apply {
                emit(value.copy(movieDetails = value.movieDetails.copy(loading = true)))
                Log.i(TAG, "getMovieDetails: $movieId")
                getSingleMovie.fetch(movieId).fold(
                    onSuccess = { movie ->
                        emit(value.copy(movieDetails = movie.copy(loading = false)))
                    },
                    onFailure = {
                        emit(
                            movieDetails.value.copy(
                                movieDetails = movieDetails.value.movieDetails.copy(
                                    loading = true
                                )
                            )
                        )
                    }
                )

            }
        }
    }

    private fun getMovieCast(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetailsState.apply {
                emit(value.copy(movieCast = value.movieCast.copy(loading = true)))
                Log.i(TAG, "getMovieCast: $movieId")
                getActors.fetch(movieId).fold(
                    onSuccess = { cast ->
                        emit(
                            value.copy(
                                movieCast = cast.copy(
                                    loading = false,
                                    actors = cast.actors
                                )
                            )
                        )
                    },
                    onFailure = {
                        emit(
                            movieDetails.value.copy(
                                movieCast = movieDetails.value.movieCast.copy(
                                    loading = true
                                )
                            )
                        )
                    }
                )

            }
        }
    }

    private fun getBackdropImages(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetailsState.apply {
                emit(value.copy(backdropImages = value.backdropImages.copy(loading = true)))
                getBackdropImages.fetch(movieId).fold(
                    onSuccess = { backdropImages ->
                        emit(
                            value.copy(
                                backdropImages = value.backdropImages.copy(
                                    loading = false,
                                    backdropImages = backdropImages.map { images ->
                                        images.filePath
                                    })
                            )
                        )
                    }, onFailure = {
                        Log.i(TAG, "getBackdropImages: ${it.stackTrace}")
                    }
                )

            }
        }
    }
}