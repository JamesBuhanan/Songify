package com.songify.feature.home.internal.di

import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.songify.library.di.FeatureScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.multibindings.Multibinds

@Module
@ContributesTo(FeatureScope::class)
interface HomeModule {
    @Multibinds
    fun presenterFactories(): Set<Presenter.Factory>

    @Multibinds
    fun viewFactories(): Set<Ui.Factory>
}