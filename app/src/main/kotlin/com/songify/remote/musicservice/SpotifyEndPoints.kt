package com.songify.remote.musicservice
//I started bringing in the musicservice from musify and then I began bringing in other folders
//with responses and tokens and what not to help get an idea of whats required to make it work
/**
 * An object that contains the different end points
 * used by [SpotifyService]. It also contains certain defaults for the
 * api calls made by [SpotifyService].
 */
object SpotifyEndPoints {
    const val SPECIFIC_ARTIST_ENDPOINT = "v1/artists/{id}"
    const val SPECIFIC_ARTIST_ALBUMS_ENDPOINT = "v1/artists/{id}/albums"
    const val SPECIFIC_ALBUM_ENDPOINT = "v1/albums/{id}"
    const val TOP_TRACKS_ENDPOINT = "v1/artists/{id}/top-tracks"
    const val SEARCH_ENDPOINT = "v1/search"
    const val API_TOKEN_ENDPOINT = "api/token"
    const val RECOMMENDATIONS_ENDPOINT = "v1/recommendations"
    const val PLAYLIST_TRACKS_ENDPOINT = "v1/playlists/{playlist_id}/tracks"
    const val NEW_RELEASES_ENDPOINT = "v1/browse/new-releases"
    const val FEATURED_PLAYLISTS = "v1/browse/featured-playlists"
    const val PLAYLISTS_FOR_BESPOKE_CATEGORY = "v1/browse/categories/{category_id}/playlists"
    const val BROWSE_CATEGORIES_FOR_COUNTRY_AND_LOCALE_ENDPOINT = "v1/browse/categories"
    const val SPECIFIC_EPISODE_ENDPOINT = "v1/episodes/{id}"
    const val SPECIFIC_SHOW_ENDPOINT = "v1/shows/{id}"
    const val SHOW_EPISODES_ENDPOINT = "v1/shows/{id}/episodes"

    object Defaults {
        const val defaultPlaylistFields =
            "id,images,name,description,owner.display_name,tracks.items,followers.total"
        val defaultSearchQueryTypes = buildSearchQueryWithTypes(*SearchQueryType.values())
    }

}