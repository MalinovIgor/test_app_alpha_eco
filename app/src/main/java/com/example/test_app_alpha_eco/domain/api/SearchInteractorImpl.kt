package com.example.test_app_alpha_eco.domain.api

import com.example.test_app_alpha_eco.data.search.SearchRepository
import com.example.test_app_alpha_eco.domain.models.Card
import com.example.test_app_alpha_eco.util.Resource
import kotlinx.coroutines.flow.Flow

class SearchInteractorImpl(private val repository: SearchRepository) : SearchInteractor {
    override fun getCardData(number: String): Flow<Resource<Card>> {
        return repository.getCardData(
            number
        )
    }
}