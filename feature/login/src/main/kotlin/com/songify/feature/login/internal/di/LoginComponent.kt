package com.songify.feature.login.internal.di

import com.songify.app.di.AppComponent
import com.songify.feature.login.internal.LoginFeature
import com.songify.library.di.FeatureScope
import com.squareup.anvil.annotations.MergeComponent
import dagger.Component

@FeatureScope
@MergeComponent(
    scope = FeatureScope::class,
    dependencies = [AppComponent::class],
)
interface LoginComponent {
    fun inject(loginFeature: LoginFeature)

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent
        ): LoginComponent
    }
}
