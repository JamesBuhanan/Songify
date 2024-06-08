package com.songify.feature.search.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.common.di.AppScope
import com.songify.feature.search.SearchScreen
import com.songify.feature.search.internal.model.Genre
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SearchPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<SearchState> {
    @Composable
    override fun present(): SearchState {
        val state by produceState<SearchState>(SearchState.Loading) {
            value = SearchState.Success(
                genres = Genre.entries,
                eventSink = {
                    when (it) {
                        SearchEvent.TappedBack -> navigator.pop()
                        SearchEvent.TappedCard -> {}
                    }
                }
            )
        }

        return state
    }

    @CircuitInject(SearchScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): SearchPresenter
    }
}
