package com.songify.feature.premium.internal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuitx.effects.toastEffect
import com.songify.feature.premium.PremiumScreen
import com.songify.library.loading.LoadingBar
import com.songify.library.premium.PremiumPlan
import dagger.hilt.components.SingletonComponent


@CircuitInject(PremiumScreen::class, SingletonComponent::class)
@Composable
fun PremiumView(
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
fun ShowPremium(
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
fun GetPremiumScreen(
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

@Composable
private fun PlanInformationCard(
    card: PremiumPlan,
    modifier: Modifier = Modifier,
    onViewPlansButtonClick: () -> Unit
) {
    val planHighlights = remember(card) {
        card.highlights.joinToString(separator = " • ")
    }
    val gradientBrush = remember(card) {
        // Offset values
        // x = 0.0f represents the leftmost area
        // y = 0.0f represent  the topmost area
        Brush.linearGradient(
            colors = listOf(
                card.gradientStartColor,
                card.gradientEndColor
            ),
            start = Offset(0.0f, 0.0f), // top left
            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY) // bottom right
        )
    }
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradientBrush)
                .padding(
                    vertical = 16.dp,
                    horizontal = 32.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PremiumCardHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                planName = card.name,
                pricing = card.pricing
            )

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                text = planHighlights
            )
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                onClick = onViewPlansButtonClick
            ) {
                Text(
                    modifier = Modifier.padding(
                        vertical = 8.dp,
                        horizontal = 24.dp
                    ),
                    text = "VIEW PLANS",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = card.termsAndConditions,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Normal
            )

        }
    }
}

@Composable
private fun PremiumCardHeader(
    planName: String,
    pricing: PremiumPlan.Pricing,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = planName,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp // a tad taller than the height of subtitle1
        )
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = pricing.cost,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = pricing.term,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
