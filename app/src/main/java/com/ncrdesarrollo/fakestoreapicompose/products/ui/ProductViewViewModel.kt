package com.ncrdesarrollo.fakestoreapicompose.products.ui

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ncrdesarrollo.fakestoreapicompose.core.navigation.ProductView
import com.ncrdesarrollo.fakestoreapicompose.products.domain.IProductsInteractor
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductViewData
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductsData
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val interactor: IProductsInteractor
) :
    ViewModel() {

    private val productData = savedStateHandle.toRoute<ProductView>()

    private var _productDataModel = MutableStateFlow<UiState<ProductViewData>>(UiState.Loading)
    var productDataModel: StateFlow<UiState<ProductViewData>> = _productDataModel

    init {
        getProductView()
    }

    private fun getProductView() {
        val productId = productData.idProduct
        viewModelScope.launch {
            try {
                if (productId != null) {
                    _productDataModel.value = UiState.Success(interactor.getDataProductById(productId))
                }
            } catch (e: Exception) {
                _productDataModel.value = UiState.Error(
                    message = e.message ?: "Error desconocido"
                )
            }
        }
    }
}