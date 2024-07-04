package com.songify.feature.login.internal.di

import com.songify.app.appComponent
import com.songify.feature.login.internal.LoginFeature


internal fun LoginFeature.inject() {
    DaggerLoginComponent
        .factory()
        .create(
            requireNotNull(appComponent)
        )
        .inject(this)
}
