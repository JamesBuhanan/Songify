package com.songify.library.detail.internal.di

import com.songify.library.detail.internal.usecase.GetTracksImpl
import com.songify.library.detail.usecase.GetTracks
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LibraryDetailModule {
    @Binds
    fun bindsGetTracks(impl: GetTracksImpl): GetTracks
}