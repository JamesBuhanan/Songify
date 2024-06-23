// Copyright (C) 2022 Slack Technologies, LLC
// SPDX-License-Identifier: Apache-2.0
package com.songify.library.circuit.di

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.Multibinds

@InstallIn(SingletonComponent::class)
@Module
interface CircuitModule {
    @Multibinds
    fun presenterFactories(): Set<Presenter.Factory>

    @Multibinds
    fun viewFactories(): Set<Ui.Factory>

    companion object {
        @Provides
        fun provideCircuit(
            presenterFactories: @JvmSuppressWildcards Set<Presenter.Factory>,
            uiFactories: @JvmSuppressWildcards Set<Ui.Factory>,
        ): Circuit {
            return Circuit.Builder()
                .apply {
                    for (factory in presenterFactories) {
                        addPresenterFactory(factory)
                    }
                    for (factory in uiFactories) {
                        addUiFactory(factory)
                    }
                }
                .build()
        }
    }
}
