package com.songify.feature.search.app.di

import com.slack.circuit.runtime.screen.Screen
import com.songify.common.di.AppScope
import com.songify.feature.search.SearchScreen
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@ContributesTo(AppScope::class)
@Module
interface SearchAppModule {
    companion object {
        @Provides
        fun providesStartScreen(): Screen = SearchScreen
    }
}