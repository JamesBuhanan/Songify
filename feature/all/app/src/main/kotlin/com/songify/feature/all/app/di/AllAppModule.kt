package com.songify.feature.all.app.di

import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.home.HomeScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AllAppModule {
    companion object {
        @Provides
        fun providesStartScreen(): Screen = HomeScreen
    }
}