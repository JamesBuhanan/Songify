package com.songify.app.api

import app.cash.better.dynamic.features.DynamicApi
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui

interface CircuitFeature : DynamicApi {
    fun presenterFactories(): Set<Presenter.Factory>
    fun uiFactories(): Set<Ui.Factory>
    fun injectFeature()
}
