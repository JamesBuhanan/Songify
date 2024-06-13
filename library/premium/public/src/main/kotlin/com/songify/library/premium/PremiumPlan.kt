package com.songify.library.premium

import androidx.compose.ui.graphics.Color

data class PremiumPlan(
    val id: String,
    val name: String,
    val highlights: List<String>,
    val termsAndConditions: String,
    val pricing: Pricing,
    val gradientStartColor: Color,
    val gradientEndColor: Color,
) {
    data class Pricing(
        val associatedCardId: String,
        val cost: String,
        val term: String
    )
}
