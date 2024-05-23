package com.songify.library.spotify

import com.songify.library.spotify.response.AlbumResponse
import com.songify.library.spotify.response.AlbumsMetadataResponse
import com.songify.library.spotify.response.ArtistResponse
import com.songify.library.spotify.response.BrowseCategoriesResponse
import com.songify.library.spotify.response.EpisodeResponse
import com.songify.library.spotify.response.EpisodesWithPreviewUrlResponse
import com.songify.library.spotify.response.FeaturedPlaylistsResponse
import com.songify.library.spotify.response.NewReleasesResponse
import com.songify.library.spotify.response.PlaylistItemsResponse
import com.songify.library.spotify.response.PlaylistsForSpecificCategoryResponse
import com.songify.library.spotify.response.SearchResultsResponse
import com.songify.library.spotify.response.ShowResponse
import com.songify.library.spotify.response.TracksWithAlbumMetadataListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * An enum that contains all the generes supported by the spotify api.
 * Note: It is not an exhaustive collection of all the genres supported
 * by the spotify api.
 * @param queryStringValue depicts the actual string value that will be
 * appended to the query when the GET request is made. This value will
 * be the returned when [SupportedSpotifyGenres.toString] is called.
 */
enum class SupportedSpotifyGenres(private val queryStringValue: String) {
    AMBIENT("ambient"),
    CHILL("chill"),
    CLASSICAL("classical"),
    DANCE("dance"),
    ELECTRONIC("electronic"),
    METAL("metal"),
    RAINY_DAY("rainy-day"),
    ROCK("rock"),
    PIANO("piano"),
    POP("pop"),
    SLEEP("sleep");

    override fun toString() = queryStringValue

}

interface SpotifyService {
    @GET(SpotifyEndPoints.SPECIFIC_ARTIST_ENDPOINT)
    suspend fun getArtistInfoWithId(
        @Path("id") artistId: String,
        @Header("Authorization") token: String,
    ): ArtistResponse

    @GET(SpotifyEndPoints.SPECIFIC_ARTIST_ALBUMS_ENDPOINT)
    suspend fun getAlbumsOfArtistWithId(
        @Path("id") artistId: String,
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("include_groups") includeGroups: String? = null,
    ): AlbumsMetadataResponse

    @GET(SpotifyEndPoints.TOP_TRACKS_ENDPOINT)
    suspend fun getTopTenTracksForArtistWithId(
        @Path("id") artistId: String,
        @Header("Authorization") token: String
    ): TracksWithAlbumMetadataListResponse

    @GET(SpotifyEndPoints.SPECIFIC_ALBUM_ENDPOINT)
    suspend fun getAlbumWithId(
        @Path("id") albumId: String,
        @Header("Authorization") token: String
    ): AlbumResponse

    @GET(SpotifyEndPoints.SEARCH_ENDPOINT)
    suspend fun search(
        @Query("q") searchQuery: String,
        @Query("market") market: String,
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("type") type: String = SpotifyEndPoints.Defaults.defaultSearchQueryTypes,
    ): SearchResultsResponse

    @GET(SpotifyEndPoints.RECOMMENDATIONS_ENDPOINT)
    suspend fun getTracksForGenre(
        @Query("seed_genres") genre: SupportedSpotifyGenres,
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 20
    ): TracksWithAlbumMetadataListResponse

    @GET(SpotifyEndPoints.PLAYLIST_TRACKS_ENDPOINT)
    suspend fun getTracksForPlaylist(
        @Path("playlist_id") playlistId: String,
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PlaylistItemsResponse

    @GET(SpotifyEndPoints.NEW_RELEASES_ENDPOINT)
    suspend fun getNewReleases(
        @Header("Authorization") token: String,
//        @Query("country") market: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): NewReleasesResponse

    @GET(SpotifyEndPoints.FEATURED_PLAYLISTS)
    suspend fun getFeaturedPlaylists(
        @Header("Authorization") token: String,
        @Query("locale") locale: String = "", // ISO 639-1 language code and an uppercase ISO 3166-1 alpha-2 country code, joined by an underscore.
        @Query("timestamp") timestamp: String = "", // A timestamp in ISO 8601 format: yyyy-MM-ddTHH:mm:ss
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): FeaturedPlaylistsResponse

    @GET(SpotifyEndPoints.BROWSE_CATEGORIES_FOR_COUNTRY_AND_LOCALE_ENDPOINT)
    suspend fun getBrowseCategories(
        @Header("Authorization") token: String,
        @Query("locale") locale: String, // ISO 639-1 language code and an uppercase ISO 3166-1 alpha-2 country code, joined by an underscore.
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): BrowseCategoriesResponse

    @GET(SpotifyEndPoints.PLAYLISTS_FOR_BESPOKE_CATEGORY)
    suspend fun getPlaylistsForCategory(
        @Header("Authorization") token: String,
        @Path("category_id") categoryId: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PlaylistsForSpecificCategoryResponse

    @GET(SpotifyEndPoints.SPECIFIC_EPISODE_ENDPOINT)
    suspend fun getEpisodeWithId(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): EpisodeResponse

    @GET(SpotifyEndPoints.SPECIFIC_SHOW_ENDPOINT)
    suspend fun getShowWithId(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Query("market") market: String
    ): ShowResponse

    @GET(SpotifyEndPoints.SHOW_EPISODES_ENDPOINT)
    suspend fun getEpisodesForShowWithId(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Query("market") market: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): EpisodesWithPreviewUrlResponse
}