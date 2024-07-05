package com.songify.library.spotify.internal.paging

import com.songify.library.session.SongifySession
import com.songify.library.spotify.internal.SpotifyService
import com.songify.library.spotify.internal.model.PlaylistsForSpecificCategoryResponse.PlaylistMetadataWithImageUrlListResponse
import com.songify.library.spotify.model.SpotifyModel
import retrofit2.HttpException
import java.io.IOException

class PlaylistsForCategoryPagingSource(
    songifySession: SongifySession,
    spotifyService: SpotifyService,
    categoryId: String,
) : SpotifyPagingSource<SpotifyModel>(
    loadBlock = { limit, offset ->
        try {
            val data = spotifyService.getPlaylistsForCategory(
                categoryId = categoryId,
                token = songifySession.requireAccessToken(),
                limit = limit,
                offset = offset,
            ).playlists.items.map { it.toPlaylistCard() }

            Result.success(data)
        } catch (httpException: HttpException) {
            Result.failure(httpException)
        } catch (ioException: IOException) {
            Result.failure(ioException)
        }
    }
)

internal fun PlaylistMetadataWithImageUrlListResponse.toPlaylistCard() = SpotifyModel.Playlist(
    id = id,
    name = name,
    caption = name,
    ownerName = ownerName.value,
    totalNumberOfTracks = totalNumberOfTracks.value.toString(),
    imageUrlString = imageUrlList.firstOrNull()?.url
)