package com.songify.feature.detail

import app.cash.better.dynamic.features.DynamicImplementation
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.songify.app.api.CircuitFeature
import com.songify.feature.detail.di.inject
import javax.inject.Inject

@DynamicImplementation
class DetailFeature : CircuitFeature {

    @Inject
    lateinit var presenterFactories: @JvmSuppressWildcards Set<Presenter.Factory>

    @Inject
    lateinit var uiFactories: @JvmSuppressWildcards Set<Ui.Factory>

    override fun presenterFactories(): Set<Presenter.Factory> {
        return presenterFactories
    }

    override fun uiFactories(): Set<Ui.Factory> {
        return uiFactories
    }

    override fun injectFeature() {
        inject(this)
    }
}
