package com.songify.feature.search.internal.di

import com.songify.feature.search.SearchFeature
import com.songify.library.di.FeatureScope
import com.songify.library.di.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.Component

@SingleIn(FeatureScope::class)
@MergeComponent(
    scope = FeatureScope::class,
//    dependencies = [AppComponent::class],
)
interface SearchComponent {
    fun inject(searchFeature: SearchFeature)

    @Component.Factory
    interface Factory {
        fun create(
//            appComponent: AppComponent
        ): SearchComponent
    }
}
