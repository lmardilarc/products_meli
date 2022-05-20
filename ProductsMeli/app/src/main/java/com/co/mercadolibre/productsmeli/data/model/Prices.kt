package com.co.mercadolibre.productsmeli.data.model


import com.co.mercadolibre.productsmeli.model.ReferencePrice
import com.google.gson.annotations.SerializedName

data class Prices(
    @SerializedName("id")
    val id: String,
    @SerializedName("payment_method_prices")
    val paymentMethodPrices: List<Any>,
    @SerializedName("prices")
    val prices: List<Price>,
    @SerializedName("purchase_discounts")
    val purchaseDiscounts: List<Any>,
    @SerializedName("reference_prices")
    val referencePrices: List<ReferencePrice>
)