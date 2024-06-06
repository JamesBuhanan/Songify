package com.songify.feature.home.internal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.songify.common.di.AppScope
import com.songify.common.ui.LoadingBar
import com.songify.feature.home.HomeScreen
import com.songify.feature.home.internal.SpotifyConstants.HOME_TAG
import com.songify.library.spotify.model.CarouselCard
import com.songify.library.spotify.model.HomeFeedCarousel
import design.HomeFeedCard

internal object SpotifyConstants {
    const val HOME_TAG = "grid"
}

@CircuitInject(HomeScreen::class, AppScope::class)
@Composable
fun HomeView(
    postsState: HomeState,
    modifier: Modifier = Modifier,
) {
    when (postsState) {
        is HomeState.Loading -> LoadingBar()
        is HomeState.Error -> toastEffect()(postsState.message)
        is HomeState.Success -> ShowHome(postsState)
    }
}

@Composable
fun ShowHome(
    homeState: HomeState.Success,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        homeState.eventSink(HomeEvent.TappedBack)
    }

    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = Modifier.testTag(HOME_TAG),
    ) {
        items(homeState.homeFeed.carousels) { item ->
            Text(
                modifier = Modifier.padding(16.dp),
                text = item.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
            )
            CarouselLazyRow(
                carousel = item,
                onHomeFeedCardClick = { homeState.eventSink(HomeEvent.TappedCard) },
            )
        }
    }
}

@Composable
private fun CarouselLazyRow(
    carousel: HomeFeedCarousel,
    onHomeFeedCardClick: (CarouselCard) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp)
) {
    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = carousel.cards, key = { it.id }) { card ->
            HomeFeedCard(
                imageUrlString = card.imageUrlString,
                caption = card.caption,
                onClick = { onHomeFeedCardClick(card) }
            )
        }
    }
}
