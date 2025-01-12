package com.example.test_app_alpha_eco.domain.api

import com.example.test_app_alpha_eco.domain.models.CardData
import com.example.test_app_alpha_eco.util.Resource
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {
    fun getCardData(number: Int): Flow<Resource<CardData>>
}