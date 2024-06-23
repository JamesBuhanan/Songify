package com.songify.feature.premium.internal

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.songify.library.premium.PremiumPlan

sealed interface PremiumEvent : CircuitUiEvent {
    data object TappedBack : PremiumEvent
    data class TappedPremiumPlan(val premiumPlan: PremiumPlan) : PremiumEvent
}

sealed interface PremiumState : CircuitUiState {
    data object Loading : PremiumState

    data class Success(
        val premiumPlans: List<PremiumPlan>,
        val eventSink: (PremiumEvent) -> Unit,
    ) : PremiumState

    data class Error(val message: String) : PremiumState
}
