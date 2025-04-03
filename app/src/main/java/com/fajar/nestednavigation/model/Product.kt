package com.fajar.nestednavigation.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val image: ImageVector,
)