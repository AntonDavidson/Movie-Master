package com.example.moviemaster

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.moviemaster.ui.MainMoviesAppScreen
import com.example.moviemaster.ui.MoviesViewModel
import com.example.moviemaster.ui.movie_details.MovieDetailsViewModel
import com.example.moviemaster.ui.theme.MovieMasterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {
            val moviesState = moviesViewModel.movies.collectAsState()
            val movieDetailsState = movieDetailsViewModel.movieDetails.collectAsState()
            MovieMasterTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    if (isOnline(this@MainActivity)) {
                        MainMoviesAppScreen(
                            moviesState = moviesState.value,
                            movieDetailsState = movieDetailsState.value,
                            movieDetailsEvent = { movieDetailsEvent ->
                                (movieDetailsViewModel::event)(movieDetailsEvent)
                            }
                        ) { moviesEvent ->
                            (moviesViewModel::event)(moviesEvent)
                        }
                    } else {
                        NoNetworkStateScreen()
                    }
                }

            }
        }
    }


    @Composable
    private fun NoNetworkStateScreen() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimaryContainer),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(200.dp))
            Text(text = "No Network Found", color = MaterialTheme.colorScheme.onPrimary)
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }

}

