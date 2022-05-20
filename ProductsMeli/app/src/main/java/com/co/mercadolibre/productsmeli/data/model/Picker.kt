package com.co.mercadolibre.productsmeli.data.model


import com.google.gson.annotations.SerializedName

data class Picker(
    @SerializedName("attributes")
    val attributes: List<AttributeX>,
    @SerializedName("picker_id")
    val pickerId: String,
    @SerializedName("picker_name")
    val pickerName: String,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("tags")
    val tags: Any
)