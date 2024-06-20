package com.songify.feature.nowplaying.internal

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuitx.effects.toastEffect
import com.songify.feature.nowplaying.NowPlayingScreen
import com.songify.library.loading.LoadingBar
import dagger.hilt.components.SingletonComponent

internal object NowPlayingConstants {
}

@CircuitInject(NowPlayingScreen::class, SingletonComponent::class)
@Composable
fun NowPlayingView(
    state: NowPlayingState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is NowPlayingState.Loading -> LoadingBar()
        is NowPlayingState.Error -> toastEffect()(state.message)
        is NowPlayingState.Success -> ShowNowPlaying(state)
    }
}

@Composable
internal fun ShowNowPlaying(
    state: NowPlayingState.Success,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        state.eventSink(NowPlayingEvent.TappedBack)
    }

    Text(text = "NOW PLAYING")
}
