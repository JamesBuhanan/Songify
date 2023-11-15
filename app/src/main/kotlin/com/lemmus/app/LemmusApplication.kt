package com.lemmus.app

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class LemmusApplication : Application() {
    private val appComponent by lazy { AppComponent.create(this) }

    fun appComponent() = appComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}
