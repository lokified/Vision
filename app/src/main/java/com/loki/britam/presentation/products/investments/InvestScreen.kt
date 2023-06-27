package com.loki.britam.presentation.products.investments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loki.britam.presentation.common.BoxItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvestScreen(
    openScreen: (String) -> Unit,
) {

    val products = listOf("Money Market Fund", "Britam Bond Plus Fund", "Balanced Fund", "Equity Fund", "Endowment Funds", "Gratuity Funds")

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Invest")
                }
            )
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp)
            ) {


                items(products) { product ->

                    BoxItem(
                        name = product,
                        modifier = Modifier.padding(8.dp),
                        height = 150.dp,
                        onClick = openScreen
                    )
                }
            }
        }
    }
}