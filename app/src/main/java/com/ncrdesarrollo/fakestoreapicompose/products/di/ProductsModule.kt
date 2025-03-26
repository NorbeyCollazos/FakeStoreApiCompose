package com.ncrdesarrollo.fakestoreapicompose.products.di

import com.ncrdesarrollo.fakestoreapicompose.products.data.IProductsDataSource
import com.ncrdesarrollo.fakestoreapicompose.products.data.ProductsApiService
import com.ncrdesarrollo.fakestoreapicompose.products.data.ProductsDatSource
import com.ncrdesarrollo.fakestoreapicompose.products.data.ProductsRepository
import com.ncrdesarrollo.fakestoreapicompose.products.domain.IProductsInteractor
import com.ncrdesarrollo.fakestoreapicompose.products.domain.IProductsRepository
import com.ncrdesarrollo.fakestoreapicompose.products.domain.ProductsInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductsModule {

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ProductsApiService {
        return retrofit.create(ProductsApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesDataSource(apiService: ProductsApiService): IProductsDataSource =
        ProductsDatSource(apiService)

    @Provides
    @Singleton
    fun providesRepository(dataSource: IProductsDataSource): IProductsRepository =
        ProductsRepository(dataSource)

    @Provides
    @Singleton
    fun providesInteractor(repository: IProductsRepository): IProductsInteractor =
        ProductsInteractor(repository)
}