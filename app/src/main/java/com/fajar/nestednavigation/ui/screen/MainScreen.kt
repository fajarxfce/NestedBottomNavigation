package com.fajar.nestednavigation.ui.screen

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fajar.nestednavigation.navigation.BottomBarScreen
import com.fajar.nestednavigation.navigation.DetailsScreen
import com.fajar.nestednavigation.navigation.detailsNavGraph
import com.fajar.nestednavigation.feature.product.ui.ProductScreen
import com.fajar.nestednavigation.feature.profile.ui.ProfileScreen
import com.fajar.nestednavigation.feature.settings.ui.SettingsScreen
import com.fajar.nestednavigation.navigation.BottomBarNavHost
import kotlinx.collections.immutable.persistentListOf
import timber.log.Timber

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    modifier : Modifier = Modifier,
) {

    val navigationBarSCreen = remember {
        persistentListOf(
            BottomBarScreen.Product,
            BottomBarScreen.Profile,
            BottomBarScreen.Settings
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
            BottomBarNavHost(
                navController = navController,
                startScreen = BottomBarScreen.Product,
                modifier = modifier.padding(scaffoldPadding),
                onNavigateDetails = {
                    Timber.d("onNavigateDetails: $it")
                },
                onNavigateSettings = {
                    Timber.d("onNavigateSettings: ")
                }
            )
        },
        modifier = modifier
    )
}

@Composable
fun RowScope.AddNavigationBarItem(
    screen: BottomBarScreen,
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
                text = stringResource(screen.title),
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            )
        },
        icon = {
            Icon(
                imageVector = when (screen) {
                    BottomBarScreen.Product -> BottomBarScreen.Product.icon
                    BottomBarScreen.Profile -> BottomBarScreen.Profile.icon
                    BottomBarScreen.Settings -> BottomBarScreen.Settings.icon
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