package com.example.test_app_alpha_eco.data.search


import com.example.test_app_alpha_eco.domain.models.Card
import com.example.test_app_alpha_eco.util.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getCardData(number: String): Flow<Resource<Card>>
}