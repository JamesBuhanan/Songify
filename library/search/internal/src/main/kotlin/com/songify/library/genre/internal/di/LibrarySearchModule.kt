package com.songify.library.genre.internal.di

import com.songify.library.genre.internal.usecase.GetGenresImpl
import com.songify.library.genre.usecase.GetGenres
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LibrarySearchModule {
    @Binds
    fun bindsGetGenres(impl: GetGenresImpl): GetGenres
}