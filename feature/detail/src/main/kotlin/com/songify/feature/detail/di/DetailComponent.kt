package com.songify.feature.detail.di

import com.songify.app.di.AppComponent
import com.songify.feature.detail.DetailFeature
import com.songify.library.di.FeatureScope
import com.songify.library.di.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.Component

@SingleIn(FeatureScope::class)
@MergeComponent(
    scope = FeatureScope::class,
    dependencies = [AppComponent::class],
)
interface DetailComponent {
    fun inject(detailFeature: DetailFeature)

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent
        ): DetailComponent
    }
}
