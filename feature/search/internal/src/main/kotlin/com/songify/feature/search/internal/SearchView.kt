package com.songify.feature.search.internal

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuitx.effects.toastEffect
import com.songify.feature.search.SearchScreen
import com.songify.feature.search.internal.ui.GenresGrid
import com.songify.library.loading.LoadingBar
import dagger.hilt.components.SingletonComponent


@CircuitInject(SearchScreen::class, SingletonComponent::class)
@Composable
fun SearchView(
    state: SearchState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is SearchState.Loading -> LoadingBar()
        is SearchState.Error -> toastEffect()(state.message)
        is SearchState.Success -> ShowSearch(state)
    }
}

@Composable
fun ShowSearch(
    state: SearchState.Success,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        state.eventSink(SearchEvent.TappedBack)
    }

    GenresGrid(
        availableGenres = state.genres,
        onGenreItemClick = { state.eventSink(SearchEvent.TappedCard) },
    )
}
