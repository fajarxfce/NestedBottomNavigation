package com.fajar.nestednavigation.feature.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fajar.nestednavigation.feature.auth.ui.LoginScreen
import com.fajar.nestednavigation.feature.auth.ui.SignUpScreen
import com.fajar.nestednavigation.navigation.Graph
import kotlinx.serialization.Serializable

@Serializable data object AuthBaseRoot
@Serializable data object LoginRoutes
@Serializable data object SignUpRoute
@Serializable data object ForgotRoute


fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    navigate(
        route = LoginRoutes,
        navOptions = navOptions
    )
}

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) {
    navigate(
        route = SignUpRoute,
        navOptions = navOptions
    )
}

fun NavController.navigateToForgot(navOptions: NavOptions? = null) {
    navigate(
        route = ForgotRoute,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.authSection(
    navController: NavHostController
){
    navigation<AuthBaseRoot>(
        startDestination = LoginRoutes
    ) {
        composable<LoginRoutes> {
            LoginScreen(
                onClick = {
                    navController.apply {
                        popBackStack()
                        navigate(Graph.MAIN)
                    }
                },
                onSignUpClick = navController::navigateToSignUp,
                onForgotClick = navController::navigateToForgot
            )
        }

        composable<SignUpRoute> {
            SignUpScreen(
                name = AuthScreen.SignUp.route,
                onClick = {}
            )
        }

        composable<ForgotRoute> {
            SignUpScreen(
                name = AuthScreen.Forgot.route,
                onClick = {}
            )
        }
    }
}