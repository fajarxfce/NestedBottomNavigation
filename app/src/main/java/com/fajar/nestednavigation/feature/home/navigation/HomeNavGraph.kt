package com.fajar.nestednavigation.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fajar.nestednavigation.feature.home.BottomBarScreen
import com.fajar.nestednavigation.feature.home.ui.ProductScreen
import com.fajar.nestednavigation.feature.home.ui.ProfileScreen
import com.fajar.nestednavigation.feature.home.ui.SettingsScreen
import com.fajar.nestednavigation.feature.home.ui.detail.InformationScreen
import com.fajar.nestednavigation.feature.home.ui.detail.OverviewScreen
import com.fajar.nestednavigation.navigation.Graph
import timber.log.Timber

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            ProductScreen(
                name = BottomBarScreen.Home.route,
                onClick = {
                    Timber.tag("TAG").d("HomeNavGraph: ")
                    navController.navigate(Graph.DETAILS)
                }
            )
        }

        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(
                name = BottomBarScreen.Profile.route,
                onClick = {

                },
            )
        }

        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen(
                name = BottomBarScreen.Settings.route,
                onClick = {

                },
            )
        }

        detailsNavGraph(navController = navController)
    }
}

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