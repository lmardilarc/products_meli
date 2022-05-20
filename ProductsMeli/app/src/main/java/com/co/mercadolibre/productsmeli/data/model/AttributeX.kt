package com.co.mercadolibre.productsmeli.data.model


import com.google.gson.annotations.SerializedName

data class AttributeX(
    @SerializedName("attribute_id")
    val attributeId: String,
    @SerializedName("template")
    val template: String
)