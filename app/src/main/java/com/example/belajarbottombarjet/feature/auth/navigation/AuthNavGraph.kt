package com.example.belajarbottombarjet.feature.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.belajarbottombarjet.feature.auth.ui.LoginScreen
import com.example.belajarbottombarjet.feature.auth.ui.SignUpScreen
import com.example.belajarbottombarjet.navigation.Graph

fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
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

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
}