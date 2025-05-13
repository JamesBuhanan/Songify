package com.songify.library.detail.fake.di

import com.songify.library.detail.fake.usecase.FakeGetTracks
import com.songify.library.detail.usecase.GetTracks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LibraryHomeModule {
    companion object {
        @Provides
        fun providesFakeGetTracks(): GetTracks = FakeGetTracks
    }
}
