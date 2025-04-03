package com.example.belajarbottombarjet.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.belajarbottombarjet.feature.home.BottomBarScreen
import com.example.belajarbottombarjet.feature.home.ui.HomeScreen
import com.example.belajarbottombarjet.feature.home.ui.ProfileScreen
import com.example.belajarbottombarjet.feature.home.ui.SettingsScreen
import com.example.belajarbottombarjet.navigation.Graph

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                name = BottomBarScreen.Home.route,
                onClick = {

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
                name = BottomBarScreen.Home.route,
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

        }
        composable(route = DetailsScreen.Overview.route) {

        }

    }
}
sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}