package com.songify.library.spotify.internal.model

import com.squareup.moshi.Json

/**
 * A response that contains the results of a search operation.
 * All the properties are nullable because a search operation
 * for just [tracks],[albums],[artists],[playlists],[shows] or
 * a combination of any of the above can be made, in which
 * case, the other properties will be null.
 */
data class SearchResultsResponse(
    val tracks: Tracks?,
    val albums: Albums?,
    val artists: Artists?,
    val playlists: Playlists?,
    val shows: Shows?,
    val episodes: Episodes?
) {
    data class Tracks(@Json(name = "items") val value: List<TrackResponseWithAlbumMetadata>)
    data class Albums(@Json(name = "items") val value: List<AlbumMetadataResponse>)
    data class Artists(@Json(name = "items") val value: List<ArtistResponse>)
    data class Playlists(@Json(name = "items") val value: List<PlaylistMetadataResponse>)
    data class Shows(@Json(name = "items") val value: List<ShowMetadataResponse>)
    data class Episodes(@Json(name = "items") val value: List<EpisodeMetadataResponse>)
}