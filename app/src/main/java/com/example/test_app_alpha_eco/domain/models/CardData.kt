package com.example.test_app_alpha_eco.domain.models

import com.example.test_app_alpha_eco.data.model.BankDto
import com.example.test_app_alpha_eco.data.model.CountryDto

data class CardData (
    val scheme: String,
    val type: String,
    val brand: String,
    val country: CountryDto,
    val bank: BankDto
)