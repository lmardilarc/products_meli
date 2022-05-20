package com.co.mercadolibre.productsmeli.data.repository.dataSourceImpl

import com.co.mercadolibre.productsmeli.data.api.ProductsAPIService
import com.co.mercadolibre.productsmeli.data.model.ProductDetailResponse
import com.co.mercadolibre.productsmeli.data.model.ProductsSearchResponse
import com.co.mercadolibre.productsmeli.data.repository.dataSource.ProductsRemoteDataSource
import retrofit2.Response

class ProductsRemoteDataSourceImpl(
    private val productsAPIService: ProductsAPIService,
) : ProductsRemoteDataSource {

    override suspend fun getProductsSearch(
        productQuery: String,
        offset: Int
    ): Response<ProductsSearchResponse> {
        return productsAPIService.getProductsSearch(productQuery = productQuery, offset = offset)
    }

    override suspend fun getProductsDetail(productId: String): Response<ProductDetailResponse> {
        return productsAPIService.getProductDetail(productId)
    }


}