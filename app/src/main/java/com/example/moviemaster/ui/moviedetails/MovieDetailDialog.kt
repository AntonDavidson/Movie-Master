package com.example.moviemaster.ui.moviedetails


import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.example.moviemaster.domain.movies.model.movies.MovieGenre
import com.example.moviemaster.ui.composecomponents.imagemanager.ImageManager.LoadImageFromNetwork
import com.example.moviemaster.ui.composecomponents.pager.HorizontalImagePager
import com.example.moviemaster.ui.moviedetails.detailstate.MovieDetailsState
import com.example.moviemaster.ui.moviedetails.model.ActorsDialogView
import com.example.moviemaster.ui.moviedetails.model.BackdropDialogView
import com.example.moviemaster.ui.moviedetails.model.MovieDetailsDialogView
import com.example.moviemaster.ui.theme.Corners
import com.example.moviemaster.ui.theme.MovieMasterTheme
import com.example.moviemaster.ui.theme.Padding
import com.gowtham.ratingbar.ComposeStars
import com.gowtham.ratingbar.RatingBarStyle


@Composable
fun MovieDetailsDialog(
    movieDetailsState: MovieDetailsState,
    dismiss: () -> Unit
) {


    val dialogProperties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
        securePolicy = SecureFlagPolicy.SecureOn
    )
    val movie = movieDetailsState.movieDetails
    val configuration = LocalConfiguration.current
    val imageHeight = configuration.screenHeightDp * 0.65
    val verticalScrollState = rememberScrollState()

    Dialog(onDismissRequest = { dismiss() }, dialogProperties) {

        Column(
            Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(Corners.size))
                .verticalScroll(verticalScrollState)
                .background(
                    MaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(Corners.size)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ViewState(
                loading = movieDetailsState.movieDetails.loading || movieDetailsState.backdropImages.loading
            ) {

                MovieImage(

                    modifier = Modifier
                        .height(imageHeight.dp),
                    movieImagePath = movie.posterPath
                )

                MovieTitle(movie = movie)

                RatingBar(

                    movie.voteAverage.toFloat()
                )

                ReleaseDate(movie)

                Genre(movie)

                MovieOverview(movie)

                Cast(cast = movieDetailsState.movieCast)

                MovieImages(
                    movieDetailsState.backdropImages
                )


            }
        }

    }
}


@Composable
fun Cast(cast: ActorsDialogView) {

    Column {
        Text(
            text = "Cast ",
            style = MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.Center),
            color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.padding(Padding.s)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(Padding.xs)
        ) {

            cast.actors.forEach { actor ->
                if (actor.picture.isNotEmpty() && actor.actorsName.isNotEmpty()) {
                    Actor(
                        name = actor.actorsName, character = actor.character.ifEmpty {
                            ""
                        }, picture = actor.picture
                    )
                }
            }

        }
    }
}

@Composable
fun Actor(name: String, character: String, picture: String) {
    val maxCharactersInARole = 12
    val roleAdaptedText = if (character.length > maxCharactersInARole) {
        character.replaceAfter("/", " others...")
    } else {
        character
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(100.dp)
        ) {
            LoadImageFromNetwork(posterPath = picture)
        }
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(Padding.s)
        )
        Text(
            text = roleAdaptedText,
            style = MaterialTheme.typography.titleSmall.copy(textAlign = TextAlign.Center),
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun MovieImages(images: BackdropDialogView) {
    HorizontalImagePager(images = images.backdropImages)
}

@Composable
private fun ReleaseDate(movie: MovieDetailsDialogView) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = movie.releaseDate,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.padding(Padding.l)
        )

    }
}

@Composable
fun RatingBar(value: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Padding.s), contentAlignment = Alignment.Center
    ) {

        ComposeStars(
            value = value,
            numOfStars = 5,
            size = 15.dp,
            spaceBetween = 5.dp,
            hideInactiveStars = false,
            style = RatingBarStyle.Default,
            painterEmpty = null,
            painterFilled = null
        )
    }


}

@Composable
private fun MovieOverview(movie: MovieDetailsDialogView) {
    Box(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(Padding.l)
        )
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Genre(movie: MovieDetailsDialogView) {
    FlowRow(horizontalArrangement = Arrangement.Center) {
        movie.genreIds.forEach { genre ->
            Box(
                modifier = Modifier
                    .padding(Padding.s)
                    .clip(shape = RoundedCornerShape(Corners.size))
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(Corners.size)
                    )
            ) {
                Text(
                    text = MovieGenre.fromId(genre.toInt())?.displayName ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(
                        Padding.s
                    )
                )
            }

        }
    }
}


@Composable
private fun MovieTitle(
    modifier: Modifier = Modifier,
    movie: MovieDetailsDialogView
) {

    Box(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.Center),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp)
        )

    }
}

@Composable
fun MovieImage(modifier: Modifier = Modifier, movieImagePath: String) {
    LoadImageFromNetwork(modifier = modifier, movieImagePath)

}

@Composable
fun ViewState(loading: Boolean, content: @Composable () -> Unit) {
    when (loading) {
        true -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(75.dp))
            }
        }

        false -> {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
    MovieMasterTheme {
        MovieDetailsDialog(
            dismiss = {},
            movieDetailsState = MovieDetailsState(
                movieDetails = MovieDetailsDialogView(
                    loading = false,
                    title = "The Man That Earn So Much He Couldn't Count",
                    overview = "The gripping tale of Jack Harper, an ordinary accountant whose life takes an extraordinary turn when he stumbles upon a mysterious algorithm that skyrockets his investments. As his wealth multiplies beyond comprehension, Jack struggles to keep up with the dizzying influx of riches. However, his newfound fortune attracts the attention of dangerous figures and puts his relationships to the test. Amidst the chaos, Jack must navigate a world where money can buy anything but peace, and learn the true value of happiness in a life overwhelmed by excess."
                ),
                backdropImages = BackdropDialogView(
                    loading = false,
                    backdropImages = listOf("", "", "", "")
                )
            )
        )
    }

}