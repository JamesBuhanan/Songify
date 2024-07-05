package com.songify.library.spotify.usecase

import com.songify.library.spotify.model.SpotifyModel.Track

interface GetTracksByAlbumId {
    suspend operator fun invoke(albumId: String): Result<List<Track>>
}