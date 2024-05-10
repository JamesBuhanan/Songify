package com.songify.common.coroutines.di

import com.songify.common.di.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@ContributesTo(AppScope::class)
@Module
class CoroutinesModule {
    @[Provides Singleton IODispatcher]
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @[Provides Singleton MainDispatcher]
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
