package com.songify.feature.home.internal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuitx.effects.toastEffect
import com.songify.feature.home.HomeScreen
import com.songify.feature.home.internal.HomeConstants.HOME_TAG
import com.songify.feature.home.internal.HomeConstants.ROW_HEADLINE_TAG
import com.songify.feature.home.internal.ui.CarouselLazyRow
import com.songify.library.composeextensions.rememberRetainedCachedPagingFlow
import com.songify.library.home.model.HomeFeed
import com.songify.library.home.model.HomeFeedCarousel
import com.songify.library.loading.LoadingBar
import dagger.hilt.components.SingletonComponent

internal object HomeConstants {
    const val HOME_TAG = "home"
    const val ROW_HEADLINE_TAG = "row_headline"
    const val CARD_TAG = "card"
}

@CircuitInject(HomeScreen::class, SingletonComponent::class)
@Composable
fun HomeView(
    state: HomeState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is HomeState.Loading -> LoadingBar()
        is HomeState.Error -> toastEffect()(state.message)
        is HomeState.Success -> {
            val stateWithCaching = state.cachePagingFlows()
            ShowHome(stateWithCaching)
        }
    }
}

@Composable
private fun HomeState.Success.cachePagingFlows(): HomeState.Success {
    val newCarousels: List<HomeFeedCarousel> = homeFeed.carousels.map {
        HomeFeedCarousel(
            id = it.id,
            title = it.title,
            spotifyModels = it.spotifyModels.rememberRetainedCachedPagingFlow()
        )
    }

    return HomeState.Success(HomeFeed(newCarousels), eventSink)
}

@Composable
internal fun ShowHome(
    state: HomeState.Success,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        state.eventSink(HomeEvent.TappedBack)
    }

    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = Modifier.testTag(HOME_TAG),
    ) {
        items(state.homeFeed.carousels) { item ->
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .testTag(ROW_HEADLINE_TAG),
                text = item.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
            )
            CarouselLazyRow(
                carousel = item,
                onHomeFeedCardClick = { state.eventSink(HomeEvent.TappedCard(it)) },
            )
        }
    }
}
