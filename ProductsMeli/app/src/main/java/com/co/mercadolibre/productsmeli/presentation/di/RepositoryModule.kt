package com.co.mercadolibre.productsmeli.presentation.di

import com.co.mercadolibre.productsmeli.data.ProductsRepositoryImpl
import com.co.mercadolibre.productsmeli.data.repository.dataSource.ProductsRemoteDataSource
import com.co.mercadolibre.productsmeli.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideProductSearchRepository(productsRemoteDataSource: ProductsRemoteDataSource): ProductsRepository {
        return ProductsRepositoryImpl(productsRemoteDataSource)
    }

}