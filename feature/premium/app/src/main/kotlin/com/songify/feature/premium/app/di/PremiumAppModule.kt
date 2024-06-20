package com.songify.feature.premium.app.di

import androidx.compose.ui.graphics.Color
import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.premium.PremiumScreen
import com.songify.library.premium.PremiumPlan
import com.songify.library.premium.usecase.GetPremiumPlans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
@Suppress("MagicNumber")
interface PremiumAppModule {
    companion object {
        val miniStart = Color(0xFF4F99F4)
        val miniEnd = Color(0xFF2F4ABC)
        val individualStart = Color(0xFF045746)
        val individualEnd = Color(0xFF16A96A)

        @Provides
        fun providesStartScreen(): Screen = PremiumScreen

        @Provides
        fun providesGetPremiumPlans(): GetPremiumPlans = object : GetPremiumPlans {
            override suspend fun invoke(): List<PremiumPlan> = listOf(
                PremiumPlan(
                    id = "premium_mini",
                    name = "Mini",
                    highlights = listOf(
                        "1 day and 1 week plans",
                        "Ad-free music on mobile",
                        "Download 30 songs on 1 mobile device",
                        "Mobile only plan"
                    ),
                    termsAndConditions = "Prices vary according to duration of plan. Terms and conditions apply.",
                    pricing = PremiumPlan.Pricing(
                        associatedCardId = "premium_mini",
                        cost = "From $7",
                        term = "For 1 day"
                    ),
                    gradientStartColor = miniStart,
                    gradientEndColor = miniEnd,
                ),
                PremiumPlan(
                    id = "premium_individual",
                    name = "Premium Individual",
                    highlights = listOf(
                        "Ad-free music listening",
                        "Download to listen offline",
                        "Debit and credit cards accepted"
                    ),
                    termsAndConditions = "Offer only for users who are new to Premium. Terms and conditions apply.",
                    pricing = PremiumPlan.Pricing(
                        associatedCardId = "premium_individual",
                        cost = "Free",
                        term = "For 1 month"
                    ),
                    gradientStartColor = individualStart,
                    gradientEndColor = individualEnd,
                ),
            )
        }
    }
}
