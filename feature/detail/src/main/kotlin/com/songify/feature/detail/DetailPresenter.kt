package com.songify.feature.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.feature.detail.data.GetTracks
import com.songify.library.di.FeatureScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DetailPresenter @AssistedInject constructor(
    private val getTracks: GetTracks,
    @Assisted private val detailScreen: DetailScreen,
    @Assisted private val navigator: Navigator,
) : Presenter<DetailState> {

    @Composable
    override fun present(): DetailState {
        val state by produceState<DetailState>(DetailState.Loading) {
            value = DetailState.Success(
                spotifyModel = detailScreen.spotifyModel,
                tracks = getTracks(detailScreen.spotifyModel),
                eventSink = {
                    when (it) {
                        is DetailEvent.TappedBack -> navigator.pop()
                        is DetailEvent.TappedTrack -> {} // navigator.goTo(NowPlayingScreen(it.track))
                    }
                }
            )
        }

        return state
    }

    @CircuitInject(DetailScreen::class, FeatureScope::class)
    @AssistedFactory
    interface Factory {
        fun create(detailScreen: DetailScreen, navigator: Navigator): DetailPresenter
    }
}
