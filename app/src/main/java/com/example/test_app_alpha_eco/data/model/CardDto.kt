package com.example.test_app_alpha_eco.data.model

import com.example.test_app_alpha_eco.data.Response
import com.google.gson.annotations.SerializedName

data class CardDto(
    @SerializedName("number")
    val number: NumberDto? = null,
    @SerializedName("scheme")
    val scheme: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("brand")
    val brand: String? = null,
    @SerializedName("prepaid")
    val prepaid: String? = null,
    @SerializedName("country")
    val country: CountryDto? = null,
    @SerializedName("bank")
    val bank: BankDto? = null
) : Response()