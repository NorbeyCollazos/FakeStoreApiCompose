package com.ncrdesarrollo.fakestoreapicompose.products.data

import okhttp3.ResponseBody
import retrofit2.Response

interface IProductsDataSource {

    suspend fun getAllProducts(): Response<ResponseBody>
}