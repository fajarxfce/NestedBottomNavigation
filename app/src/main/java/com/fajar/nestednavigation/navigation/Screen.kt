package com.fajar.nestednavigation.navigation

import androidx.annotation.StringRes
import com.fajar.nestednavigation.R

sealed class NavigationBarSCreen(
    val route: String,
    @StringRes val nameResourceId: Int
){
    data object Home : NavigationBarSCreen(
        route = "home_screen",
        nameResourceId = R.string.home_screen
    )
    data object Cart : NavigationBarSCreen(
        route = "cart_screen",
        nameResourceId = R.string.cart_screen
    )
    data object Profile : NavigationBarSCreen(
        route = "profile_screen",
        nameResourceId = R.string.profile_screen
    )
}

sealed class Screen(val route: String) {
    data object Details : Screen("details_screen")
    data object Settings : Screen("settings_screen")
    data object NavigationBar : Screen("navigation_bar_screen")
}