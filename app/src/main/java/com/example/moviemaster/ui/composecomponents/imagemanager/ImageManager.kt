package com.example.moviemaster.ui.composecomponents.imagemanager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.example.moviemaster.common.constants.BASE_POSTER_PATH
import com.example.moviemaster.ui.theme.Corners
import java.io.IOException

object ImageManager {
    private fun String.loadImageFromAssets(context: Context): Bitmap? {
        val assetManager = context.assets
        var bitmap: Bitmap? = null
        try {
            val inputStream = assetManager.open(this)
            bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmap
    }


    @Composable
    fun LoadImageFromNetwork(modifier: Modifier = Modifier, posterPath: String) {
        val context = LocalContext.current
        val bitmapImage = "viewholder.png".loadImageFromAssets(context)

        Box(
            modifier = modifier
                .clip(shape = CircleShape.copy(CornerSize(Corners.value)))
        ) {
            AsyncImage(
                model = BASE_POSTER_PATH + posterPath,
                contentDescription = "",
                placeholder = BitmapPainter(bitmapImage!!.asImageBitmap()),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }

}
