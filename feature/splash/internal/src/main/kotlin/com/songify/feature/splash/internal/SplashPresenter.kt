package com.songify.feature.splash.internal

import androidx.compose.runtime.Composable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.common.di.AppScope
import com.songify.common.session.SongifySession
import com.songify.feature.posts.PostsScreen
import com.songify.feature.splash.SplashScreen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SplashPresenter @AssistedInject constructor(
    private val songifySession: SongifySession,
    @Assisted private val navigator: Navigator,
) : Presenter<SplashState> {
    @Composable
    override fun present(): SplashState {
        return SplashState { event ->
            when (event) {
                is SplashEvent.AnimationFinished -> {
                    val test = songifySession.accessToken
                    navigator.goTo(PostsScreen)
                }
            }
        }
    }

    @CircuitInject(SplashScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): SplashPresenter
    }
}
