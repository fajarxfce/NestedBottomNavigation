package com.example.belajarbottombarjet

import com.example.belajarbottombarjet.navigation.NavigationBarSCreen

data class MainActivityUiState(
    val startScreen: NavigationBarSCreen = NavigationBarSCreen.Home,
    val isLoading: Boolean = false
)
