package com.ncrdesarrollo.fakestoreapicompose.products.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncrdesarrollo.fakestoreapicompose.products.domain.IProductsInteractor
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductsData
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val interactor: IProductsInteractor
) : ViewModel() {

    private var _uiProductsListState = MutableStateFlow<UiState<List<ProductsData>>>(UiState.Loading)
    var uiProductsListState: StateFlow<UiState<List<ProductsData>>> = _uiProductsListState

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            try {
                _uiProductsListState.value = UiState.Success(interactor.getAllProducts())

            } catch (e: Exception) {
               _uiProductsListState.value = UiState.Error(
                   message = e.message ?: "Error desconocido"
               )
            }
        }
    }


}