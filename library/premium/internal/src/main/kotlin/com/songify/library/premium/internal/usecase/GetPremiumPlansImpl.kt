package com.songify.library.premium.internal.usecase

import androidx.compose.ui.graphics.Color
import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.library.premium.PremiumPlan
import com.songify.library.premium.usecase.GetPremiumPlans
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetPremiumPlansImpl @Inject constructor() : GetPremiumPlans {
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
            gradientStartColor = Color(0xFF4F99F4),
            gradientEndColor = Color(0xFF2F4ABC),
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
            gradientStartColor = Color(0xFF045746),
            gradientEndColor = Color(0xFF16A96A),
        ),
        PremiumPlan(
            id = "premium_duo",
            name = "Premium Duo",
            highlights = listOf(
                "2 Premium accounts",
                "For couples who live together",
                "Ad-free music listening",
                "Download 10,000 songs/device, on up to 5 devices per account",
                "Choose 1, 3, 6 or 12 months of Premium",
                "Debit and credit cards accepted"
            ),
            termsAndConditions = "Offer only for users who are new to Premium. Terms and conditions apply.",
            pricing = PremiumPlan.Pricing(
                associatedCardId = "premium_duo",
                cost = "Free",
                term = "For 1 month"
            ),
            gradientStartColor = Color(0xff5992C2),
            gradientEndColor = Color(0xff3F3F76),
        ),
        PremiumPlan(
            id = "premium_family",
            name = "Premium Family",
            highlights = listOf(
                "Add-free music listening",
                "Choose 1, 3, 6 or 12 months of Premium",
                "Ad-free music listening",
                "Debit and credit cards accepted"
            ),
            termsAndConditions = "Offer only for users who are new to Premium. Terms and conditions apply.",
            pricing = PremiumPlan.Pricing(
                associatedCardId = "premium_family",
                cost = "Free",
                term = "For 1 month"
            ),
            gradientStartColor = Color(0xFF213265),
            gradientEndColor = Color(0xFF972A8E),
        ),
        PremiumPlan(
            id = "premium_student",
            name = "Premium Student",
            highlights = listOf(
                "Add-free music listening",
                "Download to listen offline",
            ),
            termsAndConditions = "Offer available only to students at an accredited higher education institution. Terms and conditions apply.",
            pricing = PremiumPlan.Pricing(
                associatedCardId = "premium_family",
                cost = "Free",
                term = "For 1 month"
            ),
            gradientStartColor = Color(0xFFF49A24),
            gradientEndColor = Color(0xFFB27049),
        )
    )
}
