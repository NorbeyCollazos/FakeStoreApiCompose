package com.ncrdesarrollo.fakestoreapicompose.products.data

import android.util.Log
import com.google.gson.JsonParser
import com.ncrdesarrollo.fakestoreapicompose.products.domain.IProductsRepository
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.Category
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductViewData
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductsData
import org.json.JSONArray
import org.json.JSONObject
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

    override suspend fun getDataProductById(productId: Int): ProductViewData {
        var model = ProductViewData()

        val response = dataSource.getDataProductById(productId)

        if (response.isSuccessful) {
            val responseString: String? = response.body()?.string() ?: response.errorBody()?.string()
            model = setJsonToModel(JSONObject(responseString.toString()))
        } else {}

        return model
    }


    private fun setJsonToModel(jsonObject: JSONObject): ProductViewData {
        val categoryObject = jsonObject.getJSONObject("category")
        val imagesArray = jsonObject.getJSONArray("images")

        val images = mutableListOf<String>()
        for (i in 0 until imagesArray.length()) {
            images.add(imagesArray.getString(i))
        }

        return ProductViewData(
            id = jsonObject.getInt("id"),
            title = jsonObject.getString("title"),
            slug = jsonObject.getString("slug"),
            price = jsonObject.getInt("price"),
            description = jsonObject.getString("description"),
            category = Category(
                id = categoryObject.getInt("id"),
                name = categoryObject.getString("name"),
                image = categoryObject.getString("image"),
                slug = categoryObject.getString("slug")
            ),
            images = images
        )
    }
}