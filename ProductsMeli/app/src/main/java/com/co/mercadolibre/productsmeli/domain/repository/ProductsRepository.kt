package com.co.mercadolibre.productsmeli.domain.repository

import com.co.mercadolibre.productsmeli.data.model.ProductDetailResponse
import com.co.mercadolibre.productsmeli.data.model.ProductsSearchResponse
import com.co.mercadolibre.productsmeli.data.util.Resource

interface ProductsRepository {

    suspend fun getProductsList(productQuery: String, offset: Int): Resource<ProductsSearchResponse>
    suspend fun getProductDetail(productId: String): Resource<ProductDetailResponse>


}