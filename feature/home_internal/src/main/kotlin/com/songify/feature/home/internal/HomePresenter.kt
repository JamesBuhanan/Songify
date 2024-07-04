package com.songify.feature.home.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.feature.home.HomeScreen
import com.songify.feature.home.internal.usecase.GetHomeFeed
import com.songify.library.di.FeatureScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class HomePresenter @AssistedInject constructor(
    private val getHomeFeed: GetHomeFeed,
    @Assisted private val navigator: Navigator,
) : Presenter<HomeState> {
    @Composable
    override fun present(): HomeState {
        val state by produceState<HomeState>(HomeState.Loading) {
            getHomeFeed().fold({ homeFeed ->
                value = HomeState.Success(
                    homeFeed = homeFeed,
                    eventSink = {
                        when (it) {
                            is HomeEvent.TappedBack -> navigator.pop()
                            is HomeEvent.TappedCard -> {}//navigator.goTo(DetailScreen(it.spotifyModel))
                        }
                    }
                )
            }, {
                value = HomeState.Error(it.message ?: "No error message")
            })
        }

        // Cache all PagingData. Is there a better way to do this?
        return state
    }

    @CircuitInject(HomeScreen::class, FeatureScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): HomePresenter
    }
}
