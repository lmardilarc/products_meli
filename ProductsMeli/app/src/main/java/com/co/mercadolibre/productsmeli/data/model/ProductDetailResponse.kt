package com.co.mercadolibre.productsmeli.data.model


import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    @SerializedName("attributes")
    val attributes: List<Attribute>,
    @SerializedName("buy_box_winner")
    val buyBoxWinner: Any,
    @SerializedName("buy_box_winner_price_range")
    val buyBoxWinnerPriceRange: Any,
    @SerializedName("children_ids")
    val childrenIds: List<Any>,
    @SerializedName("domain_id")
    val domainId: String,
    @SerializedName("family_name")
    val familyName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("main_features")
    val mainFeatures: List<MainFeature>,
    @SerializedName("name")
    val name: String,
    @SerializedName("parent_id")
    val parentId: String,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("pickers")
    val pickers: List<Picker>,
    @SerializedName("pictures")
    val pictures: List<Picture>,
    @SerializedName("settings")
    val settings: Settings,
    @SerializedName("short_description")
    val shortDescription: ShortDescription,
    @SerializedName("sold_quantity")
    val soldQuantity: Int,
    @SerializedName("status")
    val status: String
)