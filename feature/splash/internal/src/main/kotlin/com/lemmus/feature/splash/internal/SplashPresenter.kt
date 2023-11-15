package com.lemmus.feature.splash.internal

import androidx.compose.runtime.Composable
import com.lemmus.common.di.AppScope
import com.lemmus.feature.posts.PostsScreen
import com.lemmus.feature.splash.SplashScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SplashPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<SplashState> {
    @Composable
    override fun present(): SplashState {
        return SplashState { event ->
            when (event) {
                is SplashEvent.AnimationFinished -> {
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