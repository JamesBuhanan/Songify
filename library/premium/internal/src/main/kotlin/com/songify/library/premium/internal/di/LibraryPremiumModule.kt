package com.songify.library.premium.internal.di

import com.songify.library.premium.internal.usecase.GetPremiumPlansImpl
import com.songify.library.premium.usecase.GetPremiumPlans
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LibraryPremiumModule {
    @Binds
    fun bindsGetPremiumPlans(impl: GetPremiumPlansImpl): GetPremiumPlans
}