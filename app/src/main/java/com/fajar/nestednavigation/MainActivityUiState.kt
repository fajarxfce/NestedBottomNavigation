package com.fajar.nestednavigation

import com.fajar.nestednavigation.navigation.NavigationBarSCreen

data class MainActivityUiState(
    val startScreen: NavigationBarSCreen = NavigationBarSCreen.Home,
    val isLoading: Boolean = false
)
