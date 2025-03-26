package com.ncrdesarrollo.fakestoreapicompose.products.domain

import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductsData

interface IProductsInteractor {

    suspend fun getAllProducts(): List<ProductsData>
}