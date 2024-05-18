package com.songify.feature.spotify.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.common.di.AppScope
import com.songify.feature.spotify.SpotifyScreen
import com.songify.library.spotify.usecase.GetNewReleases
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SpotifyPresenter @AssistedInject constructor(
    private val getNewReleases: GetNewReleases,
    @Assisted private val navigator: Navigator,
) : Presenter<SpotifyState> {
    @Composable
    override fun present(): SpotifyState {
        val state by produceState<SpotifyState>(SpotifyState.Loading) {
            getNewReleases().fold({ newReleasesResponse ->
                value = SpotifyState.Success(
                    newReleasesResponse = newReleasesResponse,
                    eventSink = {
                        when (it) {
                            SpotifyEvent.TappedBack -> navigator.pop()
                        }
                    }
                )
            }, {
                value = SpotifyState.Error(it.message ?: "No message")
            })

        }

        return state
    }

    @CircuitInject(SpotifyScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): SpotifyPresenter
    }
}
