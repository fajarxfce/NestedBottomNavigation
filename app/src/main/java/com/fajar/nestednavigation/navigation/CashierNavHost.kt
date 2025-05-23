package com.fajar.nestednavigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fajar.nestednavigation.feature.auth.navigation.AuthBaseRoot
import com.fajar.nestednavigation.feature.auth.navigation.authSection
import com.fajar.nestednavigation.ui.screen.MainScreen

@Composable
fun CashierNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AuthBaseRoot
    ) {
        authSection(
            navController = navController
        )
        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN = "main_graph"
    const val DETAILS = "details_graph"
}