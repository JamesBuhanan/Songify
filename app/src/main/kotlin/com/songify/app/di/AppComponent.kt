package com.songify.app.di

import com.songify.library.di.AppScope
import com.songify.library.session.SongifySession
import com.songify.library.spotify.usecase.GetCategories
import com.songify.library.spotify.usecase.GetFeaturedPlaylists
import com.songify.library.spotify.usecase.GetNewReleases
import com.songify.library.spotify.usecase.GetPlaylistsForCategory
import com.songify.library.spotify.usecase.GetTracksByAlbumId
import com.songify.library.spotify.usecase.GetTracksByPlaylistId
import com.squareup.anvil.annotations.MergeComponent

@AppScope
@MergeComponent(
    scope = AppScope::class
)
interface AppComponent {
    val getNewReleases: GetNewReleases
    val getFeaturedPlaylists: GetFeaturedPlaylists
    val getCategories: GetCategories
    val getPlaylistsForCategory: GetPlaylistsForCategory
    val songifySession: SongifySession
    val getTracksByAlbumId: GetTracksByAlbumId
    val getTracksByPlaylistId: GetTracksByPlaylistId
}
