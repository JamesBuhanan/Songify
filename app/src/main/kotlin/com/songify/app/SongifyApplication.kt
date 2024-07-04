package com.songify.app

import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.songify.app.di.AppComponent
import com.songify.app.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

var appComponent: AppComponent? = null

class SongifyApplication : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        appComponent = DaggerAppComponent.create()
    }
}
