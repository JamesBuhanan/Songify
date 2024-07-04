package com.songify.library.coroutines.di

import com.songify.library.coroutines.IODispatcher
import com.songify.library.coroutines.MainDispatcher
import com.songify.library.di.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@ContributesTo(AppScope::class)
@Module
class CoroutinesModule {
    @[Provides Singleton IODispatcher]
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @[Provides Singleton MainDispatcher]
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
