package com.songify.feature.premium.internal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuitx.effects.toastEffect
import com.songify.feature.premium.PremiumScreen
import com.songify.feature.premium.internal.ui.PlanInformationCard
import com.songify.library.loading.LoadingBar
import com.songify.library.premium.PremiumPlan
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

    GetPremiumScreen(
        premiumPlans = state.premiumPlans,
        onPremiumPlanClicked = { state.eventSink(PremiumEvent.TappedPremiumPlan(it)) }
    )
}

@Composable
internal fun GetPremiumScreen(
    premiumPlans: List<PremiumPlan>,
    onPremiumPlanClicked: (PremiumPlan) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(
            top = 8.dp,
            bottom = 120.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Spacer(modifier = Modifier.statusBarsPadding())
        }
        item {
            Text(
                text = "Pick your Premium",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        }
        items(premiumPlans) {
            PlanInformationCard(
                card = it,
                onViewPlansButtonClick = { onPremiumPlanClicked(it) }
            )
        }
        item {
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}
