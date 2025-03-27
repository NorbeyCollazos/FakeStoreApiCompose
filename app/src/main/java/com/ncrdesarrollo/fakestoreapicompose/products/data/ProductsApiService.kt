package com.ncrdesarrollo.fakestoreapicompose.products.data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApiService {

    @GET("/api/v1/products/")
    suspend fun getAllProducts(): Response<ResponseBody>

    @GET("/api/v1/products/{id}")
    suspend fun getDataProductById(@Path("id") productId: Int): Response<ResponseBody>
}