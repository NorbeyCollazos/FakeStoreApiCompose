package com.ncrdesarrollo.fakestoreapicompose.products.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductsData
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.UiState

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel,
    modifier: Modifier,
    navigateToProductView: (Int) -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiProductsListState.collectAsState()
    when (val state = uiState) {
        UiState.Empty -> {}
        is UiState.Error -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()}
        UiState.Loading -> CenterLoading()
        is UiState.Success -> {
            ProductList(products = state.data, modifier) {
                navigateToProductView(it)
            }
        }
    }

}

@Composable
private fun CenterLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ProductList(products: List<ProductsData>, modifier: Modifier, onclickCard: (Int) -> Unit) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product = product) { idProduct ->
                onclickCard(idProduct)
            }
        }
    }
}