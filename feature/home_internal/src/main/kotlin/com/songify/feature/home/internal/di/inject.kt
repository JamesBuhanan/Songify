package com.songify.feature.home.internal.di

import com.songify.app.appComponent
import com.songify.feature.home.internal.HomeFeature

internal fun inject(homeFeature: HomeFeature) {
    DaggerHomeComponent
        .factory()
        .create(
            requireNotNull(appComponent)
        )
        .inject(homeFeature)
}
