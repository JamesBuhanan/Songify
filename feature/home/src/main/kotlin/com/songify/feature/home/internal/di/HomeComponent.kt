package com.songify.feature.home.internal.di

import com.songify.app.di.AppComponent
import com.songify.feature.home.internal.HomeFeature
import com.songify.library.di.FeatureScope
import com.songify.library.di.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.Component

@SingleIn(FeatureScope::class)
@MergeComponent(
    scope = FeatureScope::class,
    dependencies = [AppComponent::class],
)
interface HomeComponent {
    fun inject(homeFeature: HomeFeature)

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent
        ): HomeComponent
    }
}
