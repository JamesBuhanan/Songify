package com.songify.library.spotify.internal

import com.songify.library.spotify.internal.model.AlbumResponse
import com.songify.library.spotify.internal.model.AlbumsMetadataResponse
import com.songify.library.spotify.internal.model.ArtistResponse
import com.songify.library.spotify.internal.model.BrowseCategoriesResponse
import com.songify.library.spotify.internal.model.EpisodeResponse
import com.songify.library.spotify.internal.model.EpisodesWithPreviewUrlResponse
import com.songify.library.spotify.internal.model.FeaturedPlaylistsResponse
import com.songify.library.spotify.internal.model.NewReleasesResponse
import com.songify.library.spotify.internal.model.PlaylistItemsResponse
import com.songify.library.spotify.internal.model.PlaylistsForSpecificCategoryResponse
import com.songify.library.spotify.internal.model.SearchResultsResponse
import com.songify.library.spotify.internal.model.ShowResponse
import com.songify.library.spotify.internal.model.TracksWithAlbumMetadataListResponse
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
    @GET("v1/artists/{id}")
    suspend fun getArtistInfoWithId(
        @Path("id") artistId: String,
        @Header("Authorization") token: String,
    ): ArtistResponse

    @GET("v1/artists/{id}/albums")
    suspend fun getAlbumsOfArtistWithId(
        @Path("id") artistId: String,
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("include_groups") includeGroups: String? = null,
    ): AlbumsMetadataResponse

    @GET("v1/artists/{id}/top-tracks")
    suspend fun getTopTenTracksForArtistWithId(
        @Path("id") artistId: String,
        @Header("Authorization") token: String
    ): TracksWithAlbumMetadataListResponse

    @GET("v1/albums/{id}")
    suspend fun getAlbumWithId(
        @Path("id") albumId: String,
        @Header("Authorization") token: String
    ): AlbumResponse

    @GET("v1/search")
    suspend fun search(
        @Query("q") searchQuery: String,
        @Query("market") market: String,
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("type") type: String = SpotifyEndPoints.Defaults.defaultSearchQueryTypes,
    ): SearchResultsResponse

    @GET("v1/recommendations")
    suspend fun getTracksForGenre(
        @Query("seed_genres") genre: SupportedSpotifyGenres,
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 20
    ): TracksWithAlbumMetadataListResponse

    @GET("v1/playlists/{playlist_id}/tracks")
    suspend fun getTracksForPlaylist(
        @Path("playlist_id") playlistId: String,
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PlaylistItemsResponse

    @GET("v1/browse/new-releases")
    suspend fun getNewReleases(
        @Header("Authorization") token: String,
//        @Query("country") market: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): NewReleasesResponse

    @GET("v1/browse/featured-playlists")
    suspend fun getFeaturedPlaylists(
        @Header("Authorization") token: String,
        @Query("locale") locale: String = "", // ISO 639-1 language code and an uppercase ISO 3166-1 alpha-2 country code, joined by an underscore.
        @Query("timestamp") timestamp: String = "", // A timestamp in ISO 8601 format: yyyy-MM-ddTHH:mm:ss
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): FeaturedPlaylistsResponse

    @GET("v1/browse/categories")
    suspend fun getBrowseCategories(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): BrowseCategoriesResponse

    @GET("v1/browse/categories/{category_id}/playlists")
    suspend fun getPlaylistsForCategory(
        @Header("Authorization") token: String,
        @Path("category_id") categoryId: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PlaylistsForSpecificCategoryResponse

    @GET("v1/episodes/{id}")
    suspend fun getEpisodeWithId(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): EpisodeResponse

    @GET("v1/shows/{id}")
    suspend fun getShowWithId(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): ShowResponse

    @GET("v1/shows/{id}/episodes")
    suspend fun getEpisodesForShowWithId(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): EpisodesWithPreviewUrlResponse
}