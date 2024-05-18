package com.songify.feature.spotify.internal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import com.songify.common.di.AppScope
import com.songify.common.ui.LoadingBar
import com.songify.feature.spotify.SpotifyScreen

internal object SpotifyConstants {
    const val POST_LIST_TAG = "grid"
}

@CircuitInject(SpotifyScreen::class, AppScope::class)
@Composable
fun SpotifyView(
    postsState: SpotifyState,
    modifier: Modifier = Modifier,
) {
    when (postsState) {
        is SpotifyState.Loading -> LoadingBar()
        is SpotifyState.Success -> ShowSpotify(postsState)
    }
}

@Composable
fun ShowSpotify(postsState: SpotifyState.Success) {
    BackHandler {
        postsState.eventSink(SpotifyEvent.TappedBack)
    }

    Box {
        Text(text = "it worked")
    }
}

