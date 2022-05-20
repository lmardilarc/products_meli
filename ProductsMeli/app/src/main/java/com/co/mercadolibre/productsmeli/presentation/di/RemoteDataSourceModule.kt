package com.co.mercadolibre.productsmeli.presentation.di

import com.co.mercadolibre.productsmeli.data.api.ProductsAPIService
import com.co.mercadolibre.productsmeli.data.repository.dataSource.ProductsRemoteDataSource
import com.co.mercadolibre.productsmeli.data.repository.dataSourceImpl.ProductsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(productsAPIService: ProductsAPIService): ProductsRemoteDataSource {
        return ProductsRemoteDataSourceImpl(productsAPIService)
    }

}