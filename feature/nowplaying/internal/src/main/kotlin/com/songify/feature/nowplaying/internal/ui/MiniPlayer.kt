package com.songify.feature.nowplaying.internal.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.songify.feature.nowplaying.internal.R
import com.songify.library.design.AsyncImageWithPlaceholder
import com.songify.library.design.dynamicTheme.dynamicbackgroundmodifier.DynamicBackgroundResource
import com.songify.library.design.dynamicTheme.dynamicbackgroundmodifier.DynamicBackgroundStyle
import com.songify.library.design.dynamicTheme.dynamicbackgroundmodifier.dynamicBackground
import com.songify.library.spotify.model.Streamable

/**
 * An object that contains constants related to the [MiniPlayer]
 * composable.
 */
object MiniPlayerConstants {
    val miniPlayerHeight = 60.dp
}

@Composable
fun MiniPlayer(
    streamable: Streamable,
    isPlaybackPaused: Boolean,
    modifier: Modifier = Modifier,
    onPlayButtonClicked: () -> Unit,
    onPauseButtonClicked: () -> Unit
) {
    var isThumbnailImageLoading by remember { mutableStateOf(false) }
    val dynamicBackgroundResource = remember {
        DynamicBackgroundResource.FromImageUrl(streamable.streamInfo.imageUrl)
    }
    val dynamicBackgroundStyle = remember { DynamicBackgroundStyle.Filled() }
    Row(
        modifier = modifier
            .height(MiniPlayerConstants.miniPlayerHeight) // the height of this composable is fixed
            .clip(RoundedCornerShape(8.dp))
            .dynamicBackground(dynamicBackgroundResource, dynamicBackgroundStyle),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImageWithPlaceholder(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .aspectRatio(1f),
            model = streamable.streamInfo.imageUrl,
            contentDescription = null,
            onImageLoadingFinished = { isThumbnailImageLoading = false },
            isLoadingPlaceholderVisible = isThumbnailImageLoading,
            onImageLoading = { isThumbnailImageLoading = true },
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = streamable.streamInfo.title,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = streamable.streamInfo.subtitle,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_available_devices),
                contentDescription = null
            )
        }
        IconButton(onClick = {
            if (isPlaybackPaused) {
                // if the playback is paused, then the play button
                // would be visible. Hence, invoke the lambda that
                // is required to be executed when the play button
                // is visible.
                onPlayButtonClicked()
            } else {
                // Similarly, if the track is being played, then the pause
                // button would be visible. Hence, invoke the lambda that
                // is required to be executed when the pause button
                // is visible.
                onPauseButtonClicked()
            }
        }) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .aspectRatio(1f),
                painter = if (isPlaybackPaused) {
                    painterResource(R.drawable.ic_play_arrow_24)
                } else {
                    painterResource(R.drawable.ic_pause_24)
                },
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}

