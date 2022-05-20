package com.co.mercadolibre.productsmeli.model


import com.google.gson.annotations.SerializedName

data class ReferencePrice(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("exchange_rate_context")
    val exchangeRateContext: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("type")
    val type: String
)