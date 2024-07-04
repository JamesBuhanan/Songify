package com.songify.feature.premium.internal.di

import com.songify.feature.premium.internal.PremiumFeature
import com.songify.library.di.FeatureScope
import com.songify.library.di.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.Component

@SingleIn(FeatureScope::class)
@MergeComponent(
    scope = FeatureScope::class,
//    dependencies = [AppComponent::class],
)
interface PremiumComponent {
    fun inject(premiumFeature: PremiumFeature)

    @Component.Factory
    interface Factory {
        fun create(
//            appComponent: AppComponent
        ): PremiumComponent
    }
}