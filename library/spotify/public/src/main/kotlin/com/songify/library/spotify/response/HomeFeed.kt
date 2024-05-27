package com.songify.library.spotify.response

data class HomeFeed(
    val newReleasesResponse: NewReleasesResponse,
    val featuredPlaylistsResponse: FeaturedPlaylistsResponse,
    val playlistsForSpecificCategoryResponses: List<PlaylistsForSpecificCategoryResponse>,
)
