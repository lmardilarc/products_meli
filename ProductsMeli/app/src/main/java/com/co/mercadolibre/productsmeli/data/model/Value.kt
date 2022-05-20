package com.co.mercadolibre.productsmeli.data.model


import com.google.gson.annotations.SerializedName

data class Value(
    @SerializedName("id")
    val id: String,
    @SerializedName("meta")
    val meta: MetaX,
    @SerializedName("name")
    val name: String
)