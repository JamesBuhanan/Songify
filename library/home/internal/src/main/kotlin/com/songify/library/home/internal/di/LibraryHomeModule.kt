package com.songify.library.home.internal.di

import com.songify.library.home.internal.usecase.GetHomeFeedImpl
import com.songify.library.home.usecase.GetHomeFeed
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LibraryHomeModule {
    @Binds
    fun bindsGetHomeFeed(impl: GetHomeFeedImpl): GetHomeFeed
}