package com.co.mercadolibre.productsmeli.data.model


import com.google.gson.annotations.SerializedName

data class Settings(
    @SerializedName("has_rich_description")
    val hasRichDescription: Boolean,
    @SerializedName("listing_strategy")
    val listingStrategy: String
)