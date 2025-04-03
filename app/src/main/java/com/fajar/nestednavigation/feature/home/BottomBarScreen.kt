package com.fajar.nestednavigation.feature.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.fajar.nestednavigation.R

sealed class BottomBarScreen(
    val route: String,
    @StringRes
    val title: Int,
    val icon: ImageVector
) {
    object Product : BottomBarScreen(
        route = "PRODUCT",
        title = R.string.product,
        icon = Icons.Default.List
    )

    object Profile : BottomBarScreen(
        route = "PROFILE",
        title = R.string.profile,
        icon = Icons.Default.Person
    )

    object Settings : BottomBarScreen(
        route = "SETTINGS",
        title = R.string.setting,
        icon = Icons.Default.Settings
    )
}
