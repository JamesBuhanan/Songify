package com.lemmus.common.android.di

import com.lemmus.common.di.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module

@Module
@ContributesTo(AppScope::class)
class SharedPreferencesModule {
//    @Provides
//    fun provideSharedPreferences(context: Context): SharedPreferences {
//        return context.getSharedPreferences("MVI_PUBLIC_PREFS", ComponentActivity.MODE_PRIVATE)
//    }
}
