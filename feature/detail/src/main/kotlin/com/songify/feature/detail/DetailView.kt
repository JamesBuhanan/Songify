package com.songify.feature.detail.internal

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.android.play.core.splitcompat.SplitCompat
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuitx.effects.toastEffect
import com.songify.feature.detail.DetailEvent
import com.songify.feature.detail.DetailScreen
import com.songify.feature.detail.DetailState
import com.songify.feature.detail.R
import com.songify.feature.detail.ui.AlbumDetailScreen
import com.songify.feature.detail.ui.PlaylistDetailScreen
import com.songify.library.composeextensions.rememberRetainedCachedPagingFlow
import com.songify.library.di.FeatureScope
import com.songify.library.loading.LoadingBar
import com.songify.library.spotify.model.SpotifyModel

@CircuitInject(DetailScreen::class, FeatureScope::class)
@Composable
fun DetailView(
    state: DetailState,
    modifier: Modifier = Modifier,
) {
    // This is CRAZY.  Why do I have to do this just so we can access Resources from DFMs?
    val newContext =
        LocalContext.current.createPackageContext("com.songify", 0)
            .also { SplitCompat.install(it) }

    CompositionLocalProvider(
        LocalContext provides newContext
    ) {
        when (state) {
            is DetailState.Loading -> LoadingBar()
            is DetailState.Error -> toastEffect()(state.message)
            is DetailState.Success -> {
                val stateWithCaching = state.cachePagingFlows()
                ShowDetail(stateWithCaching)
            }
        }
    }
}

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
private fun DetailState.Success.cachePagingFlows(): DetailState.Success {
    return copy(
        tracks = tracks.rememberRetainedCachedPagingFlow()
    )
}

@Composable
internal fun ShowDetail(
    state: DetailState.Success,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        state.eventSink(DetailEvent.TappedBack)
    }

    when (state.spotifyModel) {
        is SpotifyModel.Album -> {
            AlbumDetailScreen(
                albumName = state.spotifyModel.caption,
                artistsString = state.spotifyModel.artistsString,
                yearOfRelease = state.spotifyModel.yearOfRelease,
                albumArtUrlString = state.spotifyModel.imageUrlString!!,
                trackList = state.tracks.collectAsLazyPagingItems(),
                onTrackItemClick = { state.eventSink(DetailEvent.TappedTrack(it)) },
                onBackButtonClicked = { state.eventSink(DetailEvent.TappedBack) },
                currentlyPlayingTrack = null,
            )
        }

        is SpotifyModel.Artist -> TODO()
        is SpotifyModel.Episode -> TODO()
        is SpotifyModel.Playlist -> {
            PlaylistDetailScreen(
                playlistName = state.spotifyModel.name,
                playlistImageUrlString = state.spotifyModel.imageUrlString,
                nameOfPlaylistOwner = state.spotifyModel.ownerName,
                totalNumberOfTracks = state.spotifyModel.totalNumberOfTracks,
                imageResToUseWhenImageUrlStringIsNull = R.drawable.ic_outline_account_circle_24,
                tracks = state.tracks.collectAsLazyPagingItems(),
                currentlyPlayingTrack = null,
                onTrackClicked = { state.eventSink(DetailEvent.TappedTrack(it)) },
                onBackButtonClicked = { state.eventSink(DetailEvent.TappedBack) },
            )
        }

        is SpotifyModel.Podcast -> TODO()
        is SpotifyModel.Track -> TODO()
    }
}
