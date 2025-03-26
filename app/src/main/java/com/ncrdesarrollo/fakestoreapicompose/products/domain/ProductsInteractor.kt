package com.ncrdesarrollo.fakestoreapicompose.products.domain

import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductsData
import javax.inject.Inject

class ProductsInteractor @Inject constructor(private val repository: IProductsRepository) :
    IProductsInteractor {
    override suspend fun getAllProducts(): List<ProductsData> {
        return repository.getAllProducts()
    }
}