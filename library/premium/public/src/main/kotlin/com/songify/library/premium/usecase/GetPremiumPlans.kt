package com.songify.library.premium.usecase

import com.songify.library.premium.PremiumPlan

interface GetPremiumPlans {
    suspend operator fun invoke(): List<PremiumPlan>
}