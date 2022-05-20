package com.co.mercadolibre.productsmeli.data.model


import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("id")
    val id: String,
    @SerializedName("max_height")
    val maxHeight: Int,
    @SerializedName("max_width")
    val maxWidth: Int,
    @SerializedName("suggested_for_picker")
    val suggestedForPicker: List<String>,
    @SerializedName("url")
    val url: String
)