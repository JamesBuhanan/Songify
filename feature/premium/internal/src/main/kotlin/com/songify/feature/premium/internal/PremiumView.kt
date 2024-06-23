package com.songify.feature.premium.internal

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuitx.effects.toastEffect
import com.songify.feature.premium.PremiumScreen
import com.songify.feature.premium.internal.ui.PremiumColumn
import com.songify.library.loading.LoadingBar
import dagger.hilt.components.SingletonComponent


@CircuitInject(PremiumScreen::class, SingletonComponent::class)
@Composable
internal fun PremiumView(
    state: PremiumState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is PremiumState.Loading -> LoadingBar()
        is PremiumState.Error -> toastEffect()(state.message)
        is PremiumState.Success -> ShowPremium(state)
    }
}

@Composable
internal fun ShowPremium(
    state: PremiumState.Success,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        state.eventSink(PremiumEvent.TappedBack)
    }

    PremiumColumn(
        premiumPlans = state.premiumPlans,
        onPremiumPlanClicked = { state.eventSink(PremiumEvent.TappedPremiumPlan(it)) }
    )
}
