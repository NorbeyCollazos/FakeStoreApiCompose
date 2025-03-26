package com.ncrdesarrollo.fakestoreapicompose.products.data

import android.util.Log
import com.ncrdesarrollo.fakestoreapicompose.products.domain.IProductsRepository
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductsData
import org.json.JSONArray
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val dataSource: IProductsDataSource) :
    IProductsRepository {

    override suspend fun getAllProducts(): List<ProductsData> {

        val listProducts: MutableList<ProductsData> = mutableListOf()

        runCatching { dataSource.getAllProducts() }
            .onSuccess {
                    val responseString: String? = it.body()?.string() ?: it.errorBody()?.string()
                    val jsonArray = JSONArray(responseString)
                    for (i in 0  until  jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val model = ProductsData(
                            id = jsonObject.getInt("id"),
                            title = jsonObject.getString("title"),
                            slug = jsonObject.getString("slug"),
                            price = jsonObject.getInt("price"),
                            description = jsonObject.getString("description"),
                        )
                        listProducts.add(model)
                    }

            }
            .onFailure { Log.e("errorProducts", it.message.toString()) }

        return listProducts
    }
}