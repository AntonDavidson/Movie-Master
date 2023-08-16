package com.example.moviemaster.ui.composecomponents.moviesgrid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviemaster.ui.composecomponents.buttons.IconButton
import com.example.moviemaster.ui.composecomponents.imagemanager.ImageManager.LoadImageFromNetwork
import com.example.moviemaster.ui.model.MovieDetailsMainView
import com.example.moviemaster.ui.moviesevent.MainMoviesEvent
import com.example.moviemaster.ui.theme.MovieMasterTheme
import com.example.moviemaster.ui.theme.Padding

@Composable
fun MoviesGrid(
    paddingValues: PaddingValues,
    movies: List<MovieDetailsMainView>,
    hideUI: (hide: Boolean) -> Unit,
    event: (MainMoviesEvent) -> Unit,
    onMovieClicked: (MovieDetailsMainView) -> Unit
) {

    val moviesGridState = rememberLazyGridState(initialFirstVisibleItemScrollOffset = 0)

    val currentGridIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    gridOperator(moviesGirdState = moviesGridState, currentGridIndex.intValue) {
        hideUI(it)
        currentGridIndex.intValue = moviesGridState.firstVisibleItemIndex
    }

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(Padding.xs),
        state = moviesGridState,
        columns = GridCells.Fixed(2),
        content = {
            itemsIndexed(movies) { index, movie ->
                Movie(paddingValues, index, movie, event) {
                    onMovieClicked(movie)
                }
            }


        })
}

@Composable
private fun Movie(
    paddingValues: PaddingValues,
    index: Int,
    movie: MovieDetailsMainView,
    event: (MainMoviesEvent) -> Unit,
    onMovieClicked: () -> Unit
) {

    val configuration = LocalConfiguration.current
    val imageHeight = configuration.screenHeightDp * 0.55
    val imageWidth = configuration.screenWidthDp * 0.3

    val favorite = remember {
        mutableStateOf(movie.favorite)
    }
    Box(
        modifier = Modifier
            .padding(
                top = if (index == 0 || index == 1) {
                    paddingValues.calculateTopPadding()
                } else {
                    Padding.s
                }, start = Padding.xs, end = Padding.xs
            )
            .height(imageHeight.dp)
            .width(imageWidth.dp)
            .clickable {
                onMovieClicked()
            }, contentAlignment = Alignment.Center
    )
    {
        LoadImageFromNetwork(posterPath = movie.posterPath)

        IconButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(Padding.s),
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = if (favorite.value || movie.favorite) Color.Red else {
                        Color.White
                    }
                )
            }) {

            if (movie.favorite) {
                movie.favorite = false
                event(MainMoviesEvent.RemoveFromFavorites(movieId = movie.id))
                favorite.value = false
            } else {
                movie.favorite = true
                event(MainMoviesEvent.AddToFavorites(movie))

                favorite.value = true
            }
        }

    }
}


private fun gridOperator(
    moviesGirdState: LazyGridState,
    currentGridIndex: Int,
    hideUI: (hide: Boolean) -> Unit
) {
    if (moviesGirdState.firstVisibleItemIndex > currentGridIndex) {
        hideUI(true)
    }
    if (moviesGirdState.firstVisibleItemIndex < currentGridIndex) {
        hideUI(false)
    }

}


@Preview
@Composable
fun MoviesGridPreview() {
    val mockMovieDetails = MovieDetailsMainView(
        favorite = true,
        posterPath = "",
        id = 123
    )



    MovieMasterTheme {
        MoviesGrid(paddingValues = PaddingValues(Padding.s), movies = listOf(
            mockMovieDetails,
            mockMovieDetails,
            mockMovieDetails,
            mockMovieDetails,
            mockMovieDetails,
            mockMovieDetails,
            mockMovieDetails
        ), hideUI = {}, event = {}, onMovieClicked = {})
    }
}




