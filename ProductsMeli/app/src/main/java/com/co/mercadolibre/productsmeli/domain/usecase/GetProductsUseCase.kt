package com.co.mercadolibre.productsmeli.domain.usecase

import com.co.mercadolibre.productsmeli.data.model.ProductsSearchResponse
import com.co.mercadolibre.productsmeli.data.util.Resource
import com.co.mercadolibre.productsmeli.domain.repository.ProductsRepository

class GetProductsUseCase(private val productsRepository: ProductsRepository) {

    suspend fun execute(productQuery: String, offset: Int): Resource<ProductsSearchResponse> {
        return productsRepository.getProductsList(productQuery, offset)
    }
}