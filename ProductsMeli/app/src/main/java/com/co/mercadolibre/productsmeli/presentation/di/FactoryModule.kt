package com.co.mercadolibre.productsmeli.presentation.di

import android.app.Application
import com.co.mercadolibre.productsmeli.domain.usecase.GetProductDetailUseCase
import com.co.mercadolibre.productsmeli.domain.usecase.GetProductsUseCase
import com.co.mercadolibre.productsmeli.presentation.viewmodel.ProductsSearchViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun provideProductsSearchViewModel(
        app: Application,
        getProductsUseCase: GetProductsUseCase,
        getProductDetailUseCase: GetProductDetailUseCase
    ): ProductsSearchViewModelFactory {
        return ProductsSearchViewModelFactory(
            app, getProductsUseCase, getProductDetailUseCase
        )
    }

}