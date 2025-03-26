package com.ncrdesarrollo.fakestoreapicompose.products.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProductViewScreen(
    viewModel: ProductViewViewModel,
    modifier: Modifier,
    navigateToBack: () -> Unit
) {
    viewModel.getProductView()
}