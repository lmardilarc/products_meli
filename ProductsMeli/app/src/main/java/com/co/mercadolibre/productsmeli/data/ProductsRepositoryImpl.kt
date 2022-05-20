package com.co.mercadolibre.productsmeli.data

import com.co.mercadolibre.productsmeli.data.model.ProductDetailResponse
import com.co.mercadolibre.productsmeli.data.model.ProductsSearchResponse
import com.co.mercadolibre.productsmeli.data.repository.dataSource.ProductsRemoteDataSource
import com.co.mercadolibre.productsmeli.data.util.Resource
import com.co.mercadolibre.productsmeli.domain.repository.ProductsRepository
import retrofit2.Response

class ProductsRepositoryImpl(
    private val productsRemoteDataSource: ProductsRemoteDataSource
) : ProductsRepository {

    override suspend fun getProductsList(
        productQuery: String,
        offset: Int
    ): Resource<ProductsSearchResponse> {
        return productsSearchListResponseToResource(productsRemoteDataSource.getProductsSearch(productQuery, offset))
    }

    override suspend fun getProductDetail(
        productId: String,
    ): Resource<ProductDetailResponse> {
        return productsDetailResponseToResource(productsRemoteDataSource.getProductsDetail(productId))
    }

    private fun productsSearchListResponseToResource(response: Response<ProductsSearchResponse>): Resource<ProductsSearchResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result -> return Resource.Success(result) }
        }
        return Resource.Error(response.message())
    }

    private fun productsDetailResponseToResource(response: Response<ProductDetailResponse>): Resource<ProductDetailResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result -> return Resource.Success(result) }
        }
        return Resource.Error(response.message())
    }




}