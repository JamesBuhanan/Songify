package com.songify.feature.detail.di

import com.songify.app.appComponent
import com.songify.feature.detail.DetailFeature

internal fun inject(detailFeature: DetailFeature) {
    DaggerDetailComponent
        .factory()
        .create(
            requireNotNull(appComponent)
        )
        .inject(detailFeature)
}
