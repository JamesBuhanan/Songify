package com.songify.feature.home.internal.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.songify.library.home.model.HomeFeedCarousel
import com.songify.library.spotify.model.SpotifyModel


@Composable
internal fun CarouselLazyRow(
    carousel: HomeFeedCarousel,
    onHomeFeedCardClick: (SpotifyModel) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp)
) {
    val cards: LazyPagingItems<SpotifyModel> = carousel.spotifyModels
        .collectAsLazyPagingItems()

    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = cards.itemCount,
            key = cards.itemKey { it.id },
        ) { index ->
            val card = cards[index]!!

            HomeFeedCard(
                imageUrlString = card.imageUrlString,
                caption = card.caption,
                onClick = { onHomeFeedCardClick(card) }
            )
        }
    }
}