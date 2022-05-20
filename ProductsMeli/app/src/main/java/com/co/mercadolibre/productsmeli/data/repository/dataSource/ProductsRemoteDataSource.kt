package com.co.mercadolibre.productsmeli.data.repository.dataSource

import com.co.mercadolibre.productsmeli.data.model.ProductDetailResponse
import com.co.mercadolibre.productsmeli.data.model.ProductsSearchResponse
import retrofit2.Response

interface ProductsRemoteDataSource {
    suspend fun getProductsSearch(
        productQuery: String,
        offset: Int
    ): Response<ProductsSearchResponse>

    suspend fun getProductsDetail(productId: String): Response<ProductDetailResponse>


}