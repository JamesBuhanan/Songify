package com.songify.library.home.fake.di

import com.songify.library.home.fake.usecase.FakeGetHomeFeed
import com.songify.library.home.usecase.GetHomeFeed
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LibraryHomeModule {
    companion object {
        @Provides
        fun providesFakeHomeFeed(): GetHomeFeed = FakeGetHomeFeed
    }
}