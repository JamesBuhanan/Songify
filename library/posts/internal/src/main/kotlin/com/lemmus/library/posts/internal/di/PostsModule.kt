package com.lemmus.library.posts.internal.di

import com.lemmus.common.di.AppScope
import com.lemmus.library.posts.internal.PostsService
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
@ContributesTo(AppScope::class)
interface PostsModule {
    companion object {
        @Provides
        fun providesPostsService(retrofit: Retrofit): PostsService = retrofit.create()
    }
}