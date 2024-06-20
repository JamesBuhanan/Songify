package com.songify.feature.search.app.di

import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.search.SearchScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface SearchAppModule {
    companion object {
        @Provides
        fun providesStartScreen(): Screen = SearchScreen
    }
}