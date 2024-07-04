package com.songify.feature.premium.internal.di

import com.songify.feature.premium.internal.PremiumFeature

internal fun inject(premiumFeature: PremiumFeature) {
    DaggerPremiumComponent
        .factory()
        .create(
//            (applicationContext as SongifyApplication).appComponent
        )
        .inject(premiumFeature)
}
