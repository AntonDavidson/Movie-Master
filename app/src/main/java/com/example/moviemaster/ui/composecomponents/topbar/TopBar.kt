package com.example.moviemaster.ui.composecomponents.topbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviemaster.domain.movies.model.movies.MovieCategory
import com.example.moviemaster.ui.theme.Corners
import com.example.moviemaster.ui.theme.MovieMasterTheme
import com.example.moviemaster.ui.theme.Padding


@Composable
fun TopBar(
    hideTopBar: Boolean, onCategoryClicked: (String) -> Unit
) {
    if (hideTopBar) {

        CollapsedTopBar()

    } else {
        val movieCategories = MovieCategory.entries

        ExpandedTopBar(movieCategories, onCategoryClicked)

    }
}

@Composable
private fun ExpandedTopBar(
    movieCategories: List<MovieCategory>,
    onCategoryClicked: (category: String) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    bottomEndPercent = Corners.value.value.toInt(),
                    bottomStartPercent = Corners.value.value.toInt()
                )
            )
            .background(
                MaterialTheme.colorScheme.primaryContainer.copy(0.99f)
            )

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Center
        ) {
            movieCategories.forEach { movieCategory ->
                TopBarCategory(movieCategory, onCategoryClicked)
            }
        }


    }
}

@Composable
private fun TopBarCategory(movieCategory: MovieCategory, onCategoryClicked: (String) -> Unit) {
    Button(modifier = Modifier
        .clip(CircleShape.copy(CornerSize(Corners.value)))
        .padding(Padding.l),
        elevation = ButtonDefaults.buttonElevation(10.dp),
        colors = ButtonDefaults.buttonColors(
            MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.onPrimaryContainer),
        onClick = {
            onCategoryClicked(movieCategory.apiPath)
        }) {
        Text(
            text = movieCategory.displayName,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = MaterialTheme.typography.titleSmall.fontSize
        )
    }
}


@Composable
fun CollapsedTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
    )
}


@Preview
@Composable
fun TopBarPreview() {
    MovieMasterTheme {
        TopBar(hideTopBar = false) {}
    }
}