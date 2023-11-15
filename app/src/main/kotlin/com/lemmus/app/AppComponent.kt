package com.lemmus.app

import android.app.Activity
import android.content.Context
import com.lemmus.common.di.AppScope
import com.lemmus.common.di.ApplicationContext
import com.lemmus.common.di.SingleIn
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