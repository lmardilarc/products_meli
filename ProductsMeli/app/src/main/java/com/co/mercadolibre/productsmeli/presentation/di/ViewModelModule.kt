package com.co.mercadolibre.productsmeli.presentation.di

import android.app.Application
import com.co.mercadolibre.productsmeli.domain.usecase.GetProductDetailUseCase
import com.co.mercadolibre.productsmeli.domain.usecase.GetProductsUseCase
import com.co.mercadolibre.productsmeli.presentation.viewmodel.ProductsSearchViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
    @Singleton
    @Provides
    fun provideProductSearchViewModel(
        app: Application,
        getProductsUseCase: GetProductsUseCase,
        getProductDetailUseCase: GetProductDetailUseCase
    ): ProductsSearchViewModel {

        return provideProductSearchViewModel(app, getProductsUseCase, getProductDetailUseCase)
    }

}