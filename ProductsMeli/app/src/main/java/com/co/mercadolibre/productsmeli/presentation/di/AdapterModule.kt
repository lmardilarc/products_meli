package com.co.mercadolibre.productsmeli.presentation.di

import com.co.mercadolibre.productsmeli.presentation.adapter.ProductPickersAdapter
import com.co.mercadolibre.productsmeli.presentation.adapter.ProductPicturesPagerAdapter
import com.co.mercadolibre.productsmeli.presentation.adapter.ProductsSearchAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideProductsSearchAdapter(): ProductsSearchAdapter {
        return ProductsSearchAdapter()
    }

    @Provides
    @Singleton
    fun provideProductDetailAdapter(): ProductPickersAdapter {
        return ProductPickersAdapter()
    }

    @Provides
    @Singleton
    fun provideProductPicturesPagerAdapter(): ProductPicturesPagerAdapter {
        return ProductPicturesPagerAdapter()
    }

}