package com.example.moviemaster.ui


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviemaster.domain.movies.model.movies.MovieCategory
import com.example.moviemaster.ui.compose_components.bottom_bar.BottomBar
import com.example.moviemaster.ui.compose_components.movies_grid.MoviesGrid
import com.example.moviemaster.ui.compose_components.top_bar.TopBar
import com.example.moviemaster.ui.model.MovieDetailsMainView
import com.example.moviemaster.ui.model.MoviesMainView
import com.example.moviemaster.ui.movie_details.MovieDetailsDialog
import com.example.moviemaster.ui.movie_details.detailsevent.MovieDetailsEvent
import com.example.moviemaster.ui.movie_details.detailstate.MovieDetailsState
import com.example.moviemaster.ui.movies_event.MainMoviesEvent
import com.example.moviemaster.ui.movies_state.MoviesState

private const val TAG = "MainMoviesAppScreen"

@Composable
fun MainMoviesAppScreen(
    moviesState: MoviesState,
    movieDetailsState: MovieDetailsState,
    movieDetailsEvent: (event: MovieDetailsEvent) -> Unit,
    moviesStateEvent: (event: MainMoviesEvent) -> Unit
) {
    val hideUI = remember {
        mutableStateOf(false)
    }
    val showDialog = remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.surface
            ),
        topBar = {
            TopBar(hideTopBar = hideUI.value, onCategoryClicked = { category ->
                if (category != MovieCategory.FAVORITES.apiPath) {
                    moviesStateEvent(MainMoviesEvent.GetMainMovies(category))
                } else {
                    moviesStateEvent(MainMoviesEvent.GetFavoriteMainMovies)
                }

            })
        }, bottomBar = {
            BottomBar(showBottomBar = !hideUI.value,
                page = moviesState.movies.page,
                totalPages = moviesState.movies.totalPages,
                nextPage = { moviesStateEvent(MainMoviesEvent.NextPage) },
                previousPage = { moviesStateEvent(MainMoviesEvent.PreviousPage) })
        }
    ) { paddingValues ->
        when (moviesState.loading) {
            true -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(100.dp))
                }
            }

            false -> {
                MoviesGrid(
                    paddingValues = paddingValues,
                    movies = moviesState.movies.movieDetailsMainView,
                    hideUI = { hideUI.value = it },
                    event = moviesStateEvent,
                    onMovieClicked = { movie ->
                        Log.i(TAG, "MainMoviesAppScreen: ${movie.id}")
                        movieDetailsEvent(MovieDetailsEvent.OnCreateDialog(movie.id))
                        showDialog.value = true
                    },
                )


                }
            }
        if (showDialog.value) {
            MovieDetailsDialog(movieDetailsState = movieDetailsState) {
                showDialog.value = false
            }
        }
    }
}


@Preview
@Composable
fun MainMoviesScreenPreview() {
    val mockMovieDetails = MovieDetailsMainView(
        favorite = true,
        posterPath = "",
        id = 123
    )

    val mockMovieDetailsState = MovieDetailsState()

    val mockMoviesState = MoviesState(
        loading = false, category = "Action", movies = MoviesMainView(
            movieDetailsMainView = listOf(
                mockMovieDetails,
                mockMovieDetails,
                mockMovieDetails,
                mockMovieDetails,
                mockMovieDetails,
                mockMovieDetails,
                mockMovieDetails,
                mockMovieDetails,
            )

        )
    )
    MainMoviesAppScreen(
        moviesState = mockMoviesState,
        movieDetailsState = mockMovieDetailsState,
        movieDetailsEvent = {},
        moviesStateEvent = {}
    )
}