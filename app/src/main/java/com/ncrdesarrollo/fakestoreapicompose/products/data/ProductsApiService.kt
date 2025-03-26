package com.ncrdesarrollo.fakestoreapicompose.products.data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApiService {

    @GET("/api/v1/products/")
    suspend fun getAllProducts(): Response<ResponseBody>
}