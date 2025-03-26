package com.ncrdesarrollo.fakestoreapicompose.products.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncrdesarrollo.fakestoreapicompose.products.domain.IProductsInteractor
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val interactor: IProductsInteractor) : ViewModel() {

    private var _productsList = MutableStateFlow<List<ProductsData>>(listOf())
    var productsList: StateFlow<List<ProductsData>> = _productsList

    fun getAllProducts() {
        viewModelScope.launch {
            _productsList.value = interactor.getAllProducts()
        }
    }
}