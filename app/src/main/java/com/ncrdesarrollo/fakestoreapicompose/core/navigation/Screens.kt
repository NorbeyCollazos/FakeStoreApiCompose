package com.ncrdesarrollo.fakestoreapicompose.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object ProductsList

@Serializable
data class ProductView(val idProduct: Int? = null)