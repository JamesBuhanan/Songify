package com.songify.library.bottomnavigation

import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.home.HomeScreen
import com.songify.feature.premium.PremiumScreen
import com.songify.feature.search.SearchScreen

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Search,
    BottomNavItem.Premium
)

sealed class BottomNavItem(
    val screen: Screen,
    val label: String,
    val outlinedIconVariantResourceId: Int,
    val filledIconVariantResourceId: Int
) {
    object Home : BottomNavItem(
        screen = HomeScreen,
        label = "Home",
        outlinedIconVariantResourceId = R.drawable.ic_outline_home_24,
        filledIconVariantResourceId = R.drawable.ic_filled_home_24
    )

    object Search : BottomNavItem(
        screen = SearchScreen,
        label = "Search",
        outlinedIconVariantResourceId = R.drawable.ic_outline_search_24,
        filledIconVariantResourceId = R.drawable.ic_outline_search_24
    )

    object Premium : BottomNavItem(
        screen = PremiumScreen,
        label = "Premium",
        outlinedIconVariantResourceId = R.drawable.ic_spotify_premium,
        filledIconVariantResourceId = R.drawable.ic_spotify_premium
    )
}
