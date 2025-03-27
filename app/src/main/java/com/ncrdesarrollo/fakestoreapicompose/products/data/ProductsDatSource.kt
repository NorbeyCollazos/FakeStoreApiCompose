package com.ncrdesarrollo.fakestoreapicompose.products.data

import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class ProductsDatSource @Inject constructor(private val apiService: ProductsApiService): IProductsDataSource {
    override suspend fun getAllProducts(): Response<ResponseBody> {
        return apiService.getAllProducts()
    }

    override suspend fun getDataProductById(productId: Int): Response<ResponseBody> {
        return apiService.getDataProductById(productId)
    }
}