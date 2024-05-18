package com.songify.feature.spotify.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.common.di.AppScope
import com.songify.feature.spotify.SpotifyScreen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SpotifyPresenter @AssistedInject constructor(
//    private val postsPagingSource: SpotifyPagingSource,
    @Assisted private val navigator: Navigator,
) : Presenter<SpotifyState> {
    @Composable
    override fun present(): SpotifyState {

        val state by produceState<SpotifyState>(SpotifyState.Loading) {
            value = SpotifyState.Success(
//                posts = Pager(
//                    config = PagingConfig(
//                        pageSize = 20,
//                    ),
//                    pagingSourceFactory = {
//                        postsPagingSource
//                    }
//                ).flow,
                eventSink = {
                    when (it) {
                        SpotifyEvent.TappedBack -> navigator.pop()
                    }
                }
            )
        }

        return state
    }

    @CircuitInject(SpotifyScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): SpotifyPresenter
    }
}
