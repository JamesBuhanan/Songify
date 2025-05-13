package com.songify.feature.home.internal

import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.home.HomeScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StartScreenModule {
    companion object {
        @Provides
        fun providesStartScreen(): Screen = HomeScreen
    }
}
