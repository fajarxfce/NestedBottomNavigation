package com.fajar.nestednavigation

import com.fajar.nestednavigation.feature.home.BottomBarScreen

data class MainActivityUiState(
    val startScreen: BottomBarScreen = BottomBarScreen.Product,
    val isLoading: Boolean = false
)
