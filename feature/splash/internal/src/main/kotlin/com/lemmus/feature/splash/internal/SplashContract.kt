package com.lemmus.feature.splash.internal

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

sealed interface SplashEvent : CircuitUiEvent {
    data object AnimationFinished : SplashEvent
}

data class SplashState(
    val eventSink: (SplashEvent) -> Unit,
) : CircuitUiState