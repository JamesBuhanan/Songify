package com.songify.feature.spotify.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.common.di.AppScope
import com.songify.feature.spotify.SpotifyScreen
import com.songify.library.spotify.usecase.GetAlbum
import com.songify.library.spotify.usecase.GetArtist
import com.songify.library.spotify.usecase.GetArtistAlbums
import com.songify.library.spotify.usecase.GetNewReleases
import com.songify.library.spotify.usecase.GetTopTracks
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SpotifyPresenter @AssistedInject constructor(
    private val getNewReleases: GetNewReleases,
    private val getArtist: GetArtist,
    private val getArtistAlbums: GetArtistAlbums,
    private val getTopTracks: GetTopTracks,
    private val getAlbum: GetAlbum,
    @Assisted private val navigator: Navigator,
) : Presenter<SpotifyState> {
    @Composable
    override fun present(): SpotifyState {
        val state by produceState<SpotifyState>(SpotifyState.Loading) {
//            val test = getArtist("7AB7bdCR5saJ0b9C4RuceX")
//            val test2 = getArtistAlbums("7AB7bdCR5saJ0b9C4RuceX")
            getTopTracks("7AB7bdCR5saJ0b9C4RuceX").fold({
                val albumId = it.tracks.first().albumMetadata.id
                val test4 = getAlbum(albumId)
            },{

            })
            getNewReleases().fold({ newReleasesResponse ->
                value = SpotifyState.Success(
                    newReleasesResponse = newReleasesResponse,
                    eventSink = {
                        when (it) {
                            SpotifyEvent.TappedBack -> navigator.pop()
                        }
                    }
                )
            }, {
                value = SpotifyState.Error(it.message ?: "No message")
            })

        }

        return state
    }

    @CircuitInject(SpotifyScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): SpotifyPresenter
    }
}
