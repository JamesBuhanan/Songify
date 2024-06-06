package com.songify.library.spotify.internal

object SpotifyEndPoints {
    object Defaults {
        const val defaultPlaylistFields =
            "id,images,name,description,owner.display_name,tracks.items,followers.total"
        val defaultSearchQueryTypes = buildSearchQueryWithTypes(*SearchQueryType.values())
    }
}
