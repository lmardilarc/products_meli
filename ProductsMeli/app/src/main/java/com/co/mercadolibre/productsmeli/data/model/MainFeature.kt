package com.co.mercadolibre.productsmeli.data.model


import com.google.gson.annotations.SerializedName

data class MainFeature(
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("text")
    val text: String,
    @SerializedName("type")
    val type: String
)