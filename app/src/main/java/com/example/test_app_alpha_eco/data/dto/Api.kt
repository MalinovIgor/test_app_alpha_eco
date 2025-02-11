package com.example.test_app_alpha_eco.data.dto

import com.example.test_app_alpha_eco.data.model.CardDto
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("https://lookup.binlist.net/{card_number}")
    suspend fun getCardData(@Path("card_number") cardNumber: Int): CardDto
}