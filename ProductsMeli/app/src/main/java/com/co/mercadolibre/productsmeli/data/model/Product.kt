package com.co.mercadolibre.productsmeli.data.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("picker_label")
    val pickerLabel: String,
    @SerializedName("picture_id")
    val pictureId: String,
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("thumbnail")
    val thumbnail: String
)