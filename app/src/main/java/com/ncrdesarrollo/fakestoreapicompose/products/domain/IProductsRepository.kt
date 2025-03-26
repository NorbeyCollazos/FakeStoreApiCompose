package com.ncrdesarrollo.fakestoreapicompose.products.domain

import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductsData

interface IProductsRepository {

    suspend fun getAllProducts(): List<ProductsData>
}