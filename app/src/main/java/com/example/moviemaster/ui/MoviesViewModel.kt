package com.example.moviemaster.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemaster.domain.movies.model.movies.MovieCategory
import com.example.moviemaster.domain.movies.usecases.movies.Favorites
import com.example.moviemaster.domain.movies.usecases.movies.GetMovies
import com.example.moviemaster.ui.model.MovieDetailsMainView
import com.example.moviemaster.ui.moviesevent.MainMoviesEvent
import com.example.moviemaster.ui.moviesstate.MoviesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MoviesViewModel"

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovies: GetMovies,
    private val favorites: Favorites

) : ViewModel() {
    private val moviesState = MutableStateFlow(MoviesState())
    val movies: StateFlow<MoviesState> = this.moviesState


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getMovies(category = "upcoming")
        }
    }

    fun event(movieEvent: MainMoviesEvent) {
        when (movieEvent) {
            is MainMoviesEvent.GetMainMovies -> {
                if (movieEvent.category != MovieCategory.FAVORITES.apiPath) {
                    getMovies(movieEvent.category)
                }
            }

            is MainMoviesEvent.GetFavoriteMainMovies -> {
                getFavorites()
            }

            is MainMoviesEvent.AddToFavorites -> {
                addMovieToFavorites(movieEvent.movieDetailsMainView)
            }

            is MainMoviesEvent.RemoveFromFavorites -> {
                removeMovieFromFavorites(movieEvent.movieId)
                if (movies.value.category === MovieCategory.FAVORITES.displayName) {
                  removeMovieAndUpdateFavorites(movieEvent.movieId)
                }
            }

            is MainMoviesEvent.NextPage -> {
                nextPage()
            }

            is MainMoviesEvent.PreviousPage -> {
                previousPage()
            }
        }
    }


    private fun getMovies(category: String, page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesState.apply {
                emit(value.copy(loading = true))

                getMovies.fetch(category, page).fold(
                    onSuccess = { movies ->
                        emit(
                            value.copy(
                                loading = false,
                                category = category,
                                movies = movies
                            )
                        )
                    }, onFailure = {
                        moviesState.apply {
                            emit(moviesState.value.copy())

                            Log.i(TAG, "getMovies: FAILED")
                        }
                    }

                )
            }
        }
    }


    private fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {


            moviesState.apply { emit(value.copy(loading = true)) }
            moviesState.apply {
                emit(
                    value.copy(
                        loading = false,
                        category = MovieCategory.FAVORITES.displayName,
                        movies = favorites.getMovies()
                    )
                )
            }


        }
    }

    private fun addMovieToFavorites(movieDetailsMainView: MovieDetailsMainView) {
        viewModelScope.launch(Dispatchers.IO) {
            favorites.addMovie(movieDetailsMainView)
        }
    }

    private fun removeMovieFromFavorites(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            favorites.removeMovie(movieId)
        }
    }

    private fun removeMovieAndUpdateFavorites(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesState.apply {
                emit(
                    value.copy(
                        loading = false,
                        category = MovieCategory.FAVORITES.displayName,
                        movies = favorites.removeMovieAndUpdateFavorites(movieId)
                    )
                )
            }
        }
    }

    private fun getMoviesStateCategory(): String {
        return movies.value.category
    }

    private fun getMoviesStatePage(): Int {
        return movies.value.movies.page
    }

    private fun getMoviesTotalPages(): Int {
        return ((movies.value.movies.totalPages))
    }


    private fun nextPage() {
        if (getMoviesStatePage() < getMoviesTotalPages()) {
            getMovies(category = getMoviesStateCategory(), getMoviesStatePage() + 1)
        }
    }

    private fun previousPage() {
        if (getMoviesStatePage() > 1) {
            getMovies(category = getMoviesStateCategory(), getMoviesStatePage() - 1)
        }
    }
}




