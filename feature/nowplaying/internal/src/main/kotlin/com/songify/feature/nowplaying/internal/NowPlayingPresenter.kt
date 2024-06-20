package com.songify.feature.nowplaying.internal

import androidx.compose.runtime.Composable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.feature.nowplaying.NowPlayingScreen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent

class NowPlayingPresenter @AssistedInject constructor(
    @Assisted private val nowPlayingScreen: NowPlayingScreen,
    @Assisted private val navigator: Navigator,
) : Presenter<NowPlayingState> {
    @Composable
    override fun present(): NowPlayingState {
        return NowPlayingState.Success(
            track = nowPlayingScreen.track,
            eventSink = {
                when (it) {
                    is NowPlayingEvent.TappedBack -> navigator.pop()
                }
            }
        )
    }

    @CircuitInject(NowPlayingScreen::class, SingletonComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(nowPlayingScreen: NowPlayingScreen, navigator: Navigator): NowPlayingPresenter
    }
}
