package com.co.mercadolibre.productsmeli.data.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    @SerializedName("buying_mode")
    val buyingMode: String,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("catalog_product_id")
    val catalogProductId: String?,
    @SerializedName("domain_id")
    val domainId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("installments")
    val installments: Installments,
    @SerializedName("order_backend")
    val orderBackend: Int,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("prices")
    val prices: Prices,
    @SerializedName("sold_quantity")
    val soldQuantity: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("thumbnail_id")
    val thumbnailId: String,
    @SerializedName("title")
    val title: String,
)