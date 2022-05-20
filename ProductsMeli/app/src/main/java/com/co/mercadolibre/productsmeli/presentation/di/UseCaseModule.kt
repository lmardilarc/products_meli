package com.co.mercadolibre.productsmeli.presentation.di

import com.co.mercadolibre.productsmeli.domain.repository.ProductsRepository
import com.co.mercadolibre.productsmeli.domain.usecase.GetProductDetailUseCase
import com.co.mercadolibre.productsmeli.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetProductsUseCase(productsRepository: ProductsRepository): GetProductsUseCase {
        return GetProductsUseCase(productsRepository)
    }

    @Singleton
    @Provides
    fun provideGetProductDetailUseCase(productsRepository: ProductsRepository): GetProductDetailUseCase {
        return GetProductDetailUseCase(productsRepository)
    }


}