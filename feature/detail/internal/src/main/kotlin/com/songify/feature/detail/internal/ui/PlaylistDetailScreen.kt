package com.songify.feature.detail.internal.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.songify.library.design.dynamicTheme.dynamicbackgroundmodifier.DynamicBackgroundResource
import com.songify.library.design.dynamicTheme.dynamicbackgroundmodifier.dynamicBackground
import com.songify.library.spotify.model.SpotifyModel.Track
import kotlinx.coroutines.launch

@Composable
fun PlaylistDetailScreen(
    playlistName: String,
    playlistImageUrlString: String?,
    nameOfPlaylistOwner: String,
    totalNumberOfTracks: String,
    @DrawableRes imageResToUseWhenImageUrlStringIsNull: Int,
    tracks: LazyPagingItems<Track>,
    currentlyPlayingTrack: Track?,
    onBackButtonClicked: () -> Unit,
    onTrackClicked: (Track) -> Unit,
) {
    var isLoadingPlaceholderForAlbumArtVisible by remember { mutableStateOf(false) }
    val lazyListState = rememberLazyListState()
    val isAppBarVisible by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex > 0 }
    }
    val dynamicBackgroundResource = remember {
        if (playlistImageUrlString == null) {
            DynamicBackgroundResource.Empty
        } else {
            DynamicBackgroundResource.FromImageUrl(playlistImageUrlString)
        }
    }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = 120.dp
            ),
            state = lazyListState
        ) {
            items(items = listOf("")) {}
            headerWithImageItem(
                dynamicBackgroundResource = dynamicBackgroundResource,
                playlistName = playlistName,
                playlistImageUrlString = playlistImageUrlString,
                imageResToUseWhenImageUrlStringIsNull = imageResToUseWhenImageUrlStringIsNull,
                nameOfPlaylistOwner = nameOfPlaylistOwner,
                totalNumberOfTracks = totalNumberOfTracks,
                isLoadingPlaceholderForAlbumArtVisible = isLoadingPlaceholderForAlbumArtVisible,
                onImageLoading = { isLoadingPlaceholderForAlbumArtVisible = true },
                onImageLoaded = { isLoadingPlaceholderForAlbumArtVisible = false },
                onBackButtonClicked = onBackButtonClicked
            )

            items(
                count = tracks.itemCount,
                key = tracks.itemKey { it.id },
            ) { index ->
                val track = tracks[index]!!

                CompactTrackCard(
                    track = track,
                    onClick = onTrackClicked,
                    isLoadingPlaceholderVisible = false,
                    isCurrentlyPlaying = track == currentlyPlayingTrack,
                    isAlbumArtVisible = true,
                    subtitleTextStyle = LocalTextStyle.current.copy(
                        fontWeight = FontWeight.Thin,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.38f),
                    ),
                    contentPadding = PaddingValues(16.dp)
                )
            }

            item {
                Spacer(
                    modifier = Modifier
                        .windowInsetsBottomHeight(WindowInsets.navigationBars)
                        .padding(bottom = 16.dp)
                )
            }
        }
        AnimatedVisibility(
            visible = isAppBarVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            DetailScreenTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .statusBarsPadding(),
                title = playlistName,
                onBackButtonClicked = onBackButtonClicked,
                dynamicBackgroundResource = dynamicBackgroundResource,
                onClick = {
                    coroutineScope.launch { lazyListState.animateScrollToItem(0) }
                }
            )
        }
    }
}

private fun LazyListScope.headerWithImageItem(
    dynamicBackgroundResource: DynamicBackgroundResource,
    playlistName: String,
    playlistImageUrlString: String?,
    @DrawableRes imageResToUseWhenImageUrlStringIsNull: Int,
    nameOfPlaylistOwner: String,
    totalNumberOfTracks: String,
    isLoadingPlaceholderForAlbumArtVisible: Boolean,
    onImageLoading: () -> Unit,
    onImageLoaded: (Throwable?) -> Unit,
    onBackButtonClicked: () -> Unit
) {
    item {
        Column(
            modifier = Modifier
                .dynamicBackground(dynamicBackgroundResource)
                .statusBarsPadding()
        ) {
            ImageHeaderWithMetadata(
                title = playlistName,
                headerImageSource = if (playlistImageUrlString == null) {
                    HeaderImageSource.ImageFromDrawableResource(
                        resourceId = imageResToUseWhenImageUrlStringIsNull
                    )
                } else {
                    HeaderImageSource.ImageFromUrlString(playlistImageUrlString)
                },
                subtitle = "by $nameOfPlaylistOwner • $totalNumberOfTracks tracks",
                onBackButtonClicked = onBackButtonClicked,
                isLoadingPlaceholderVisible = isLoadingPlaceholderForAlbumArtVisible,
                onImageLoading = onImageLoading,
                onImageLoaded = onImageLoaded,
                additionalMetadataContent = { }
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}
