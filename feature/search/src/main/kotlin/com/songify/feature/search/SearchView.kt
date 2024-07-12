package com.songify.feature.search

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuitx.effects.toastEffect
import com.songify.feature.search.ui.GenresGrid
import com.songify.library.di.FeatureScope
import com.songify.library.loading.LoadingBar


@CircuitInject(SearchScreen::class, FeatureScope::class)
@Composable
fun SearchView(
    state: SearchState,
    modifier: Modifier = Modifier,
) {
    // This is CRAZY.  Why do I have to do this just so we can access Resources from DFMs?
    val newContext = LocalContext.current.createPackageContext("com.songify", 0)

    CompositionLocalProvider(
        LocalContext provides newContext
    ) {
        when (state) {
            is SearchState.Loading -> LoadingBar()
            is SearchState.Error -> toastEffect()(state.message)
            is SearchState.Success -> ShowSearch(state)
        }
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
