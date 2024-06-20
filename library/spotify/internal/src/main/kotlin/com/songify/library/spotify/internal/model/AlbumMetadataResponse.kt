package com.songify.library.spotify.internal.model

import com.songify.library.spotify.model.SpotifyModel
import com.squareup.moshi.Json

/**
 * A response object that contains metadata about a specific album.
 * Note: The object only contains metadata. It doesn't contain
 * the track list. [AlbumResponse] contains the track list in addition
 * to the metadata.
 */
data class AlbumMetadataResponse(
    val id: String,
    val name: String,
    @Json(name = "album_type") val albumType: String, // album,single or compilation
    val artists: List<ArtistInfoResponse>,
    val images: List<ImageResponse>,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "release_date_precision") val releaseDatePrecision: String, // year, month or day
    @Json(name = "total_tracks") val totalTracks: Int,
    val type: String
) {
    /**
     * A response object associated with [AlbumMetadataResponse] that contains information
     * about an artist.
     */
    data class ArtistInfoResponse(
        val id: String,
        val name: String
    )
}

internal fun AlbumMetadataResponse.toAlbumCard() = SpotifyModel.Album(
    id = id,
    imageUrlString = images.firstOrNull()?.url,
    caption = name,
    artistsString = artists.joinToString(", ") { name },
    yearOfRelease = releaseDate.substring(0..3) // yyyy-mm-dd
)
