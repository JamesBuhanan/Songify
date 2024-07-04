package com.songify.library.spotify.internal.model

import com.songify.library.spotify.model.SpotifyModel.Track
import com.squareup.moshi.Json

/**
 * A response object that represents an album. It also contains additional
 * meta data about the album and includes information about the
 * artists.
 */
data class AlbumResponse(
    val id: String,
    val name: String,
    @Json(name = "album_type") val albumType: String, // album,single or compilation
    val artists: List<ArtistResponseWithNullableImagesAndFollowers>,
    val images: List<ImageResponse>,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "release_date_precision") val releaseDatePrecision: String, // year, month or day
    @Json(name = "total_tracks") val totalTracks: Int,
    val tracks: TracksWithoutAlbumMetadataListResponse
) {
    /**
     * A data class that contains the list of tracks associated with
     * a particular [AlbumResponse].
     */
    data class TracksWithoutAlbumMetadataListResponse(val items: List<TrackResponseWithoutAlbumMetadataResponse>)

    /**
     * A response object that contains information about a specific track
     * without containing metadata about the album.
     * [TrackResponseWithAlbumMetadata] contains both, information about
     * the track and the metadata about the associated album.
     */
    data class TrackResponseWithoutAlbumMetadataResponse(
        val id: String,
        val name: String,
        @Json(name = "preview_url") val previewUrl: String,
        @Json(name = "is_playable") val isPlayable: Boolean?,
        val explicit: Boolean,
        @Json(name = "duration_ms") val durationInMillis: Int
    )

    /**
     * A response object that contains information about an Artist.
     * [ArtistResponse] mandates these two parameters whereas this object
     * makes [images] and [followers] as nullable type.
     */
    data class ArtistResponseWithNullableImagesAndFollowers(
        val id: String,
        val name: String,
        val images: List<ImageResponse>?,
        val followers: ArtistResponse.Followers?
    )
}

fun AlbumResponse.getTracks(): List<Track> =
    tracks.items.map { trackResponse ->
        trackResponse.toTrack(
            albumArtImageUrlString = images.getImageResponseForImageSize(MapperImageSize.LARGE).url,
            albumArtistsString = artists.joinToString(",") { it.name }
        )
    }

fun AlbumResponse.TrackResponseWithoutAlbumMetadataResponse.toTrack(
    albumArtImageUrlString: String,
    albumArtistsString: String
) = Track(
    id = id,
    caption = name,
    imageUrlString = albumArtImageUrlString,
    artistsString = albumArtistsString,
    trackUrlString = previewUrl
)

fun List<ImageResponse>.getImageResponseForImageSize(imageSize: MapperImageSize): ImageResponse {
    if (this.isEmpty()) error("The list of images is empty!")
    if (this.size < 3) return this.last()
    return when (imageSize) {
        MapperImageSize.LARGE -> this[0]
        MapperImageSize.MEDIUM -> this[1]
        MapperImageSize.SMALL -> this[2]
    }
}

enum class MapperImageSize { SMALL, MEDIUM, LARGE }