package com.songify.feature.all.app.di

import com.slack.circuit.runtime.screen.Screen
import com.songify.common.di.AppScope
import com.songify.feature.home.HomeScreen
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@ContributesTo(AppScope::class)
@Module
interface AllAppModule {
    companion object {
        @Provides
        fun providesStartScreen(): Screen = HomeScreen
    }
}