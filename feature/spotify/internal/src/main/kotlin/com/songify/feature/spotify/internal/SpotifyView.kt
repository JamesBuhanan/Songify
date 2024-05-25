package com.songify.feature.spotify.internal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.slack.circuit.codegen.annotations.CircuitInject
import com.songify.common.di.AppScope
import com.songify.common.ui.LoadingBar
import com.songify.feature.spotify.SpotifyScreen

internal object SpotifyConstants {
    const val POST_LIST_TAG = "grid"
}

@CircuitInject(SpotifyScreen::class, AppScope::class)
@Composable
fun SpotifyView(
    postsState: SpotifyState,
    modifier: Modifier = Modifier,
) {
    when (postsState) {
        is SpotifyState.Loading -> LoadingBar()
        is SpotifyState.Error -> Text(postsState.message)
        is SpotifyState.Success -> ShowSpotify(postsState)
    }
}

@Composable
fun ShowSpotify(
    postsState: SpotifyState.Success,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        postsState.eventSink(SpotifyEvent.TappedBack)
    }

    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = Modifier.testTag("blah"),
    ) {
        items(postsState.newReleasesResponse.albums.items) { item ->
            ImageResponse(item.images.first().url)
            Text(text = item.name)
        }
    }
}

@Composable
fun ImageResponse(
    thumbnailUrl: Any?,
    iconTransformationBuilder: ImageRequest.Builder.() -> Unit = {}
) {
    Image(
        painter = rememberImagePainter(
            data = thumbnailUrl,
            builder = iconTransformationBuilder
        ),
        modifier = Modifier
            .size(88.dp)
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
        contentDescription = "Album item thumbnail picture",
    )
}
