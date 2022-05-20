package com.co.mercadolibre.productsmeli.data.api

import com.co.mercadolibre.productsmeli.data.model.ProductDetailResponse
import com.co.mercadolibre.productsmeli.data.model.ProductsSearchResponse
import com.co.mercadolibre.productsmeli.domain.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsAPIService {
    @GET("sites/{siteId}/search")
    suspend fun getProductsSearch(
        @Path("siteId") siteId: String = Const.SITE_ID,
        @Query("limit") limit: Int = Const.PRODUCTS_LIMIT_PAGINATION,
        @Query("q") productQuery: String,
        @Query("offset") offset: Int
    ): Response<ProductsSearchResponse>


    @GET("products/{productId}")
    suspend fun getProductDetail(
        @Path("productId") productId: String,
    ): Response<ProductDetailResponse>


}