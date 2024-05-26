package com.songify.feature.home.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.common.di.AppScope
import com.songify.feature.home.HomeScreen
import com.songify.library.spotify.usecase.GetBrowseCategories
import com.songify.library.spotify.usecase.GetFeaturedPlaylists
import com.songify.library.spotify.usecase.GetNewReleases
import com.songify.library.spotify.usecase.GetPlaylistsForCategory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.async

class HomePresenter @AssistedInject constructor(
    private val getNewReleases: GetNewReleases,
    private val getFeaturedPlaylists: GetFeaturedPlaylists,
    private val getPlaylistsForCategory: GetPlaylistsForCategory,
    private val getBrowseCategories: GetBrowseCategories,
    @Assisted private val navigator: Navigator,
) : Presenter<HomeState> {
    @Composable
    override fun present(): HomeState {
        val state by produceState<HomeState>(HomeState.Loading) {
            val newReleasesTask = async { getNewReleases() }
            val featuredPlaylistsTask = async { getFeaturedPlaylists(locale = "", timestamp = "") }
            val playlistsForCategoryTask = async { getPlaylistsForCategory(categoryId = "") }

            newReleasesTask.await()
            featuredPlaylistsTask.await()
            playlistsForCategoryTask.await()

            val newReleases = newReleasesTask.getCompleted()
            val featuredPlaylists = featuredPlaylistsTask.getCompleted()
            val playlistsForCategory = playlistsForCategoryTask.getCompleted()

            if (
                newReleases.isFailure ||
                featuredPlaylists.isFailure ||
                playlistsForCategory.isFailure
            ) {
                value = HomeState.Error("OH NOOOOOO!")
            } else {
                value = HomeState.Success(
                    newReleasesResponse = newReleases.getOrThrow(),
                    featuredPlaylistsResponse = featuredPlaylists.getOrThrow(),
                    playlistsForSpecificCategoryResponse = playlistsForCategory.getOrThrow(),
                    eventSink = {
                        when (it) {
                            HomeEvent.TappedBack -> navigator.pop()
                        }
                    }
                )
            }
        }

        return state
    }

    @CircuitInject(HomeScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): HomePresenter
    }
}
