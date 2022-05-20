package com.co.mercadolibre.productsmeli.presentation.di

import com.co.mercadolibre.productsmeli.BuildConfig
import com.co.mercadolibre.productsmeli.data.api.ProductsAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideProductsSearchAPIService(retrofit: Retrofit): ProductsAPIService {
        return retrofit.create(ProductsAPIService::class.java)
    }


}