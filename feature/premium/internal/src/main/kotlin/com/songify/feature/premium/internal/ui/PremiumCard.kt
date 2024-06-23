package com.songify.feature.premium.internal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.songify.library.premium.PremiumPlan

@Composable
internal fun PremiumCard(
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
