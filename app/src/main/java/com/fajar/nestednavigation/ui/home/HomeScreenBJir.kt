package com.fajar.nestednavigation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fajar.nestednavigation.model.Product

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenBjir(
){
    Scaffold {
        Box(
            modifier = Modifier.padding(16.dp)
        ){
            LazyColumn(
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
            ) {
                var listProduct = listOf<Product>()
                for (i in 1..100) {
                    listProduct = listProduct + Product(
                        id = i,
                        name = "Product $i",
                        price = (i * 10).toDouble(),
                        image = Icons.Default.Home
                    )
                }

                items(
                    count = listProduct.size,
                    key = { index -> listProduct[index].id },
                    itemContent = { index ->
                        val product = listProduct[index]
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = product.name,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Price: ${product.price}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }

                    }
                )
            }

        }
    }
}