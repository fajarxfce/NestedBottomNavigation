package com.fajar.nestednavigation.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fajar.nestednavigation.feature.product.ui.ProductScreen
import com.fajar.nestednavigation.feature.profile.ui.ProfileScreen
import kotlinx.serialization.Serializable

@Serializable
object SettingsRoute

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    navigate(
        route = SettingsRoute,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.settingsSection(
    onClick: () -> Unit,
) {
    composable<SettingsRoute> {
        ProfileScreen(
            name = "Profile BEJIR",
            onClick = onClick,
        )
    }
}