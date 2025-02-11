package com.example.test_app_alpha_eco.domain.models

import com.example.test_app_alpha_eco.data.Response
import com.example.test_app_alpha_eco.data.model.BankDto
import com.example.test_app_alpha_eco.data.model.CountryDto
import com.example.test_app_alpha_eco.data.model.NumberDto

data class Card (
    val number: NumberDto? = null,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: String? = null,
    val country: CountryDto? = null,
    val bank: BankDto? = null
): Response()