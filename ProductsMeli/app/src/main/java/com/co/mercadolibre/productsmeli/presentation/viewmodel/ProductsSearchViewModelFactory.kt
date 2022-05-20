package com.co.mercadolibre.productsmeli.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.co.mercadolibre.productsmeli.domain.usecase.GetProductDetailUseCase
import com.co.mercadolibre.productsmeli.domain.usecase.GetProductsUseCase

class ProductsSearchViewModelFactory(
    private val app: Application,
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsSearchViewModel(
            app,
            getProductsUseCase,
            getProductDetailUseCase
        ) as T
    }
}