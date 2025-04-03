package com.fajar.nestednavigation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fajar.nestednavigation.feature.information.ui.InformationScreen
import com.fajar.nestednavigation.feature.overview.ui.OverviewScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            InformationScreen(
                name = DetailsScreen.Information.route,
                onClick = {
                    navController.navigate(DetailsScreen.Overview.route)
                },
            )
        }
        composable(route = DetailsScreen.Overview.route) {
            OverviewScreen(
                name = DetailsScreen.Overview.route,
                onClick = {}
            )
        }

    }
}
sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}