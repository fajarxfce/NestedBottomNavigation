package com.fajar.nestednavigation.navigation

import androidx.annotation.StringRes
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fajar.nestednavigation.R
import com.fajar.nestednavigation.feature.product.ui.ProductScreen
import com.fajar.nestednavigation.feature.profile.ui.ProfileScreen
import com.fajar.nestednavigation.feature.settings.ui.SettingsScreen
import com.fajar.nestednavigation.navigation.detailsNavGraph


@Composable
fun BottomBarNavHost(
    navController: NavHostController,
    startScreen: BottomBarScreen,
    onNavigateDetails: (String) -> Unit,
    onNavigateSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        modifier = modifier
    ) {
        composable(route = BottomBarScreen.Product.route) {
            ProductScreen(
                name = BottomBarScreen.Product.route,
                onClick = {
                    navController.navigate(DetailsScreen.Information.route)
                },
            )
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen(
                name = BottomBarScreen.Profile.route,
                onClick = {
                },
            )
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(
                name = BottomBarScreen.Profile.route,
                onClick = {
                },
            )
        }
        detailsNavGraph(navController)
    }
}

sealed class BottomBarScreen(
    val route: String,
    @StringRes
    val title: Int,
    val icon: ImageVector
) {
    object Product : BottomBarScreen(
        route = "PRODUCT",
        title = R.string.product,
        icon = Icons.Default.List
    )

    object Profile : BottomBarScreen(
        route = "PROFILE",
        title = R.string.profile,
        icon = Icons.Default.Person
    )

    object Settings : BottomBarScreen(
        route = "SETTINGS",
        title = R.string.setting,
        icon = Icons.Default.Settings
    )
}