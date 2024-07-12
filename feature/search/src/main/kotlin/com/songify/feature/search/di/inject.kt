package com.songify.feature.search.di

import com.songify.feature.search.SearchFeature
import com.songify.feature.search.internal.di.DaggerSearchComponent

internal fun inject(searchFeature: SearchFeature) {
    DaggerSearchComponent.factory()
        .create(
//            appComponent
        )
        .inject(searchFeature)
}
