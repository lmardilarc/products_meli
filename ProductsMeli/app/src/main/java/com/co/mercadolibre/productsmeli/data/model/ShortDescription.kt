package com.co.mercadolibre.productsmeli.data.model


import com.google.gson.annotations.SerializedName

data class ShortDescription(
    @SerializedName("content")
    val content: String,
    @SerializedName("type")
    val type: String
)