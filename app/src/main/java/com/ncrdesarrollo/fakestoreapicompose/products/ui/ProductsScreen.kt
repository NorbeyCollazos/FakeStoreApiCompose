package com.ncrdesarrollo.fakestoreapicompose.products.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductsData

@Composable
fun ProductsScreen(viewModel: ProductsViewModel, modifier: Modifier){
    viewModel.getAllProducts()
    val productsList by viewModel.productsList.collectAsState()
    ProductList(productsList, modifier)
}

@Composable
fun ProductList(products: List<ProductsData>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product = product) {
                Log.i("idProduct", it.toString())
            }
        }
    }
}