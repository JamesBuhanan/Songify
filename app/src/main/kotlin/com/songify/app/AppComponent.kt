package com.songify.app

import android.app.Activity
import android.content.Context
import com.songify.common.di.AppScope
import com.songify.common.di.ApplicationContext
import com.songify.common.di.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Provider

@MergeComponent(AppScope::class)
@SingleIn(AppScope::class)
interface AppComponent {
    val activityProviders: Map<Class<out Activity>, @JvmSuppressWildcards Provider<Activity>>

    @Component.Factory
    interface Factory {
        fun create(@ApplicationContext @BindsInstance context: Context): AppComponent
    }

    companion object {
        fun create(context: Context): AppComponent = DaggerAppComponent.factory().create(context)
    }
}