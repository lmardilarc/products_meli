package com.co.mercadolibre.productsmeli.data.model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("rgb")
    val rgb: String,
    @SerializedName("value")
    val value: Boolean
)