package com.co.mercadolibre.productsmeli.data.model


import com.google.gson.annotations.SerializedName

data class Attribute(
    @SerializedName("id")
    val id: String,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("name")
    val name: String,
    @SerializedName("value_id")
    val valueId: String,
    @SerializedName("value_name")
    val valueName: String,
    @SerializedName("values")
    val values: List<Value>
)