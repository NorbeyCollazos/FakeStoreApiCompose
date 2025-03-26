package com.ncrdesarrollo.fakestoreapicompose.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ncrdesarrollo.fakestoreapicompose.products.ui.ProductViewScreen
import com.ncrdesarrollo.fakestoreapicompose.products.ui.ProductViewViewModel
import com.ncrdesarrollo.fakestoreapicompose.products.ui.ProductsScreen
import com.ncrdesarrollo.fakestoreapicompose.products.ui.ProductsViewModel

@Composable
fun NavigationWrapper(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ProductsList) {

        composable<ProductsList> {
            val viewModel = hiltViewModel<ProductsViewModel>()
            ProductsScreen(viewModel, modifier) {idProduct ->
                navController.navigate(ProductView(idProduct))
            }
        }

        composable<ProductView> {
            val viewModel = hiltViewModel<ProductViewViewModel>()
            ProductViewScreen(viewModel, modifier){
                navController.popBackStack()
            }
        }

    }
}