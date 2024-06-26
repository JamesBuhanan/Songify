package com.songify.library.spotify.internal.model

import com.songify.library.spotify.model.SpotifyModel
import com.squareup.moshi.Json

/**
 * A response object that contains only the metadata associated with a
 * particular playlist. [PlaylistResponse] contains additional
 * tracks and followers properties.
 */
data class PlaylistMetadataResponse(
    val id: String,
    val name: String,
    val images: List<ImageResponse>,
    @Json(name = "owner") val ownerName: OwnerNameWrapper,
    @Json(name = "tracks") val totalNumberOfTracks: TotalNumberOfTracksWrapper
) {
    data class TotalNumberOfTracksWrapper(@Json(name = "total") val value: Int)
    data class OwnerNameWrapper(@Json(name = "display_name") val value: String)
}

internal fun PlaylistMetadataResponse.toPlaylistCard() = SpotifyModel.Playlist(
    id = id,
    caption = name,
    imageUrlString = images.firstOrNull()?.url,
    name = name,
    ownerName = ownerName.value,
    totalNumberOfTracks = totalNumberOfTracks.value.toString(),
)
