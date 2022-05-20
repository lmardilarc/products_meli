package com.co.mercadolibre.productsmeli.domain.usecase

import com.co.mercadolibre.productsmeli.data.model.ProductDetailResponse
import com.co.mercadolibre.productsmeli.data.util.Resource
import com.co.mercadolibre.productsmeli.domain.repository.ProductsRepository

class GetProductDetailUseCase(private val productsRepository: ProductsRepository) {

    suspend fun execute(productId: String): Resource<ProductDetailResponse> {
        return productsRepository.getProductDetail(productId)
    }

}