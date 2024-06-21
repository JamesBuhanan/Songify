package com.songify.library.coroutines.di

import com.songify.library.coroutines.IODispatcher
import com.songify.library.coroutines.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CoroutinesModule {
    @[Provides Singleton IODispatcher]
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @[Provides Singleton MainDispatcher]
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
