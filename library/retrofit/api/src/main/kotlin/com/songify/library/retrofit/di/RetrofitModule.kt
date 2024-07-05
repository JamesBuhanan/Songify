package com.songify.library.retrofit.di

import com.songify.library.di.AppScope
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@ContributesTo(AppScope::class)
interface RetrofitModule {
    companion object {
        private const val API_URL = "https://api.spotify.com/"

        @Provides
        @AppScope
        fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
            return OkHttpClient
                .Builder()
                .build()
        }

        @Provides
        @AppScope
        fun provideMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        @Provides
        @AppScope
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            moshi: Moshi,
        ): Retrofit {
            return Retrofit
                .Builder()
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(API_URL)
                .build()
        }
    }
}
