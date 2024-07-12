package com.songify.feature.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.feature.search.data.usecase.GetGenres
import com.songify.library.di.FeatureScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SearchPresenter @AssistedInject constructor(
    private val getGenres: GetGenres,
    @Assisted private val navigator: Navigator,
) : Presenter<SearchState> {
    @Composable
    override fun present(): SearchState {
        val state by produceState<SearchState>(SearchState.Loading) {
            value = SearchState.Success(
                genres = getGenres(),
                eventSink = {
                    when (it) {
                        is SearchEvent.TappedBack -> navigator.pop()
                        is SearchEvent.TappedCard -> {}
                    }
                }
            )
        }

        return state
    }

    @CircuitInject(SearchScreen::class, FeatureScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): SearchPresenter
    }
}
