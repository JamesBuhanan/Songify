package com.songify.feature.search.internal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuitx.effects.toastEffect
import com.songify.common.di.AppScope
import com.songify.common.ui.LoadingBar
import com.songify.feature.search.SearchScreen


@CircuitInject(SearchScreen::class, AppScope::class)
@Composable
fun SearchView(
    postsState: SearchState,
    modifier: Modifier = Modifier,
) {
    when (postsState) {
        is SearchState.Loading -> LoadingBar()
        is SearchState.Error -> toastEffect()(postsState.message)
        is SearchState.Success -> ShowSearch(postsState)
    }
}

@Composable
fun ShowSearch(
    searchState: SearchState.Success,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        searchState.eventSink(SearchEvent.TappedBack)
    }

    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
    ) {
        items(searchState.genres) { item ->
            Text(
                modifier = Modifier.padding(16.dp),
                text = item.caption,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}

