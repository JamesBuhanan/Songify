package com.songify.library.spotify.response

import com.squareup.moshi.Json

/**
 * A response that contains a list of albums together with additional
 * metadata.
 */
data class AlbumsMetadataResponse(
    val items: List<AlbumMetadataResponse>,
    val limit: Int, // indicates the number of items in the list
    @Json(name = "next") val nextPageUrlString: String,
    val offset: Int,
    @Json(name = "previous") val previousPageUrlString: String?,
    @Json(name = "total") val totalNumberOfItemsAvailable: Int
)
