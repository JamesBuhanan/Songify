package com.songify.feature.nowplaying

import com.slack.circuit.runtime.screen.Screen
import com.songify.library.spotify.model.SpotifyModel.Track
import kotlinx.parcelize.Parcelize

@Parcelize
data class NowPlayingScreen(val track: Track) : Screen
