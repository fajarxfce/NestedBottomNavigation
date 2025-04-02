package com.example.belajarbottombarjet.navigation

import android.graphics.drawable.Icon
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.collections.immutable.persistentListOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.belajarbottombarjet.ui.cart.CartScreen
import com.example.belajarbottombarjet.ui.home.HomeScreen
import com.example.belajarbottombarjet.ui.profile.ProfileScreen

@Composable
fun NavigationBarScaffold(
    startScreen: NavigationBarSCreen,
    onNavigateDetails: (String) -> Unit,
    onNavigateSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val navigationBarSCreen = remember {
        persistentListOf(
            NavigationBarSCreen.Home,
            NavigationBarSCreen.Cart,
            NavigationBarSCreen.Profile
        )
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showNavigationBar = navigationBarSCreen.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (showNavigationBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    tonalElevation = 0.dp
                ) {
                    navigationBarSCreen.forEach { screen ->
                        AddNavigationBarItem(
                            screen = screen,
                            currentDestination = currentDestination,
                            navController = navController,
                        )
                    }
                }
            }
        },
        content = { scaffoldPadding ->
            NavigationBarNavHost(
                navController = navController,
                startScreen = startScreen,
                onNavigateDetails = onNavigateDetails,
                onNavigateSettings = onNavigateSettings,
                modifier = modifier.padding(scaffoldPadding)
            )
        },
        modifier = modifier
    )
}

@Composable
fun NavigationBarNavHost(
    navController: NavHostController,
    startScreen: NavigationBarSCreen,
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
        composable(route = NavigationBarSCreen.Home.route) {
            HomeScreen()
        }
        composable(route = NavigationBarSCreen.Cart.route) {
            CartScreen()
        }
        composable(route = NavigationBarSCreen.Profile.route) {
            ProfileScreen()
        }
    }
}

@Composable
fun RowScope.AddNavigationBarItem(
    screen: NavigationBarSCreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val selected = currentDestination?.hierarchy?.any { destination ->
        destination.route == screen.route
    } == true

    NavigationBarItem(
        label = {
            Text(
                text = stringResource(screen.nameResourceId),
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            )
        },
        icon = {
            Icon(
                imageVector = when (screen) {
                    NavigationBarSCreen.Home -> Icons.Rounded.Home
                    NavigationBarSCreen.Cart -> Icons.Rounded.ShoppingCart
                    NavigationBarSCreen.Profile -> Icons.Rounded.ShoppingCart
                },
                contentDescription = null
            )
        },
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onSurface,
            selectedTextColor = MaterialTheme.colorScheme.onSurface,
            indicatorColor = MaterialTheme.colorScheme.background,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = modifier
    )
}