package com.fajar.nestednavigation.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fajar.nestednavigation.feature.auth.ui.LoginScreen
import com.fajar.nestednavigation.feature.auth.ui.SignUpScreen
import com.fajar.nestednavigation.navigation.Graph

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                onClick = {
                    navController.apply {
                        popBackStack()
                        navigate(Graph.MAIN)
                    }
                },
                onSignUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                },
                onForgotClick = {
                    navController.navigate(AuthScreen.Forgot.route)
                }
            )
        }

        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen(
                name = AuthScreen.SignUp.route,
                onClick = {}
            )
        }

        composable(route = AuthScreen.Forgot.route) {
            SignUpScreen(
                name = AuthScreen.Forgot.route,
                onClick = {}
            )
        }
    }
}