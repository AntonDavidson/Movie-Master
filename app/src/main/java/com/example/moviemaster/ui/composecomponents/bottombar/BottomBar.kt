package com.example.moviemaster.ui.composecomponents.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import com.example.moviemaster.ui.composecomponents.buttons.IconButton
import com.example.moviemaster.ui.theme.Corners
import com.example.moviemaster.ui.theme.Padding

@Composable
fun BottomBar(
    showBottomBar: Boolean,
    page: Int,
    totalPages: Int,
    nextPage: () -> Unit,
    previousPage: () -> Unit
) {
    if (showBottomBar) {

        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                icon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        modifier = Modifier.rotate(-90f),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }, modifier = Modifier
                    .padding(Padding.l)
                    .align(Alignment.CenterStart)
            ) {
                previousPage()

            }



            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(Corners.value))
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(Corners.value)
                    )
                    .align(Alignment.Center), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$page/$totalPages",
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
                    modifier = Modifier.padding(Padding.s)
                )
            }





            IconButton(
                icon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        modifier = Modifier.rotate(90f),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }, modifier = Modifier
                    .padding(Padding.l)
                    .align(Alignment.CenterEnd)
            ) {
                nextPage()
            }


        }
    }
}
