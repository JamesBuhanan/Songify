package com.songify.library.detail.internal.usecase

import androidx.paging.PagingData
import com.songify.library.detail.usecase.GetTracks
import com.songify.library.spotify.model.SpotifyModel
import com.songify.library.spotify.model.SpotifyModel.Album
import com.songify.library.spotify.model.SpotifyModel.Track
import com.songify.library.spotify.usecase.GetTracksByAlbumId
import com.songify.library.spotify.usecase.GetTracksByPlaylistId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTracksImpl @Inject constructor(
    private val getTracksByAlbumId: GetTracksByAlbumId,
    private val getTracksByPlaylistId: GetTracksByPlaylistId,
) : GetTracks {
    override suspend operator fun invoke(spotifyModel: SpotifyModel): Flow<PagingData<Track>> {
        var flow = flowOf<PagingData<Track>>()

        when (spotifyModel) {
            is Album -> {
                getTracksByAlbumId(spotifyModel.id).fold({
                    flow = flowOf(PagingData.from(it))
                }, {

                })
            }

            is SpotifyModel.Artist -> TODO()
            is SpotifyModel.Episode -> TODO()
            is SpotifyModel.Playlist -> {
                flow = getTracksByPlaylistId(spotifyModel.id)
            }

            is SpotifyModel.Podcast -> TODO()
            is Track -> TODO()
        }

        return flow
    }
}
