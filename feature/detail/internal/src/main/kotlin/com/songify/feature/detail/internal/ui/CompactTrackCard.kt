package com.songify.feature.detail.internal.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.songify.library.spotify.model.SpotifyModel.Track

@Composable
fun CompactTrackCard(
    track: Track,
    onClick: (Track) -> Unit,
    isLoadingPlaceholderVisible: Boolean,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    isCurrentlyPlaying: Boolean = false,
    isAlbumArtVisible: Boolean = true,
    onImageLoading: ((Track) -> Unit)? = null,
    onImageLoadingFinished: ((Track, Throwable?) -> Unit)? = null,
    titleTextStyle: TextStyle = LocalTextStyle.current,
    subtitleTextStyle: TextStyle = LocalTextStyle.current,
    contentPadding: PaddingValues = MusifyCompactTrackCardDefaults.defaultContentPadding
) {
    val trackPlayingTextStyle = LocalTextStyle.current.copy(
        color = MaterialTheme.colorScheme.primary
    )
    // set alpha based on whether the track is available for playback
    CompactListItemCard(
        modifier = modifier,
        backgroundColor = backgroundColor,
        shape = shape,
        cardType = ListItemCardType.TRACK,
        thumbnailImageUrlString = if (isAlbumArtVisible) track.imageUrlString else null,
        title = track.caption,
        subtitle = track.artistsString,
        onClick = { onClick(track) },
        onTrailingButtonIconClick = {},
        isLoadingPlaceHolderVisible = isLoadingPlaceholderVisible,
        onThumbnailLoading = { onImageLoading?.invoke(track) },
        onThumbnailImageLoadingFinished = { throwable ->
            onImageLoadingFinished?.invoke(track, throwable)
        },
        titleTextStyle = if (isCurrentlyPlaying) trackPlayingTextStyle else titleTextStyle,
        subtitleTextStyle = subtitleTextStyle,
        contentPadding = contentPadding
    )
}

/**
 * Contains default values used by [CompactTrackCard].
 */
object MusifyCompactTrackCardDefaults {
    val defaultContentPadding = PaddingValues(
        horizontal = 16.dp,
        vertical = 8.dp
    )
}
