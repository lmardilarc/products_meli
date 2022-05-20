package com.co.mercadolibre.productsmeli.data.model
import com.google.gson.annotations.SerializedName

data class ProductsSearchResponse(
    @SerializedName("country_default_time_zone")
    val countryDefaultTimeZone: String,
    @SerializedName("filters")
    val filters: List<Any>,
    @SerializedName("paging")
    val paging: Paging,
    @SerializedName("query")
    val query: String,
    @SerializedName("results")
    var results: MutableList<Result>,

)