package com.songify.feature.detail

import com.slack.circuit.runtime.screen.Screen
import com.songify.library.spotify.model.SpotifyModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailScreen(val spotifyModel: SpotifyModel) : Screen
