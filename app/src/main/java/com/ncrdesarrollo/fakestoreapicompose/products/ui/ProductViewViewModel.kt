package com.ncrdesarrollo.fakestoreapicompose.products.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.ncrdesarrollo.fakestoreapicompose.core.navigation.ProductView
import com.ncrdesarrollo.fakestoreapicompose.products.domain.IProductsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val interactor: IProductsInteractor
) :
    ViewModel() {

    private val productData = savedStateHandle.toRoute<ProductView>()

    fun getProductView() {
        val productId = productData.idProduct
        Log.i("idProduct", productId.toString())
    }
}