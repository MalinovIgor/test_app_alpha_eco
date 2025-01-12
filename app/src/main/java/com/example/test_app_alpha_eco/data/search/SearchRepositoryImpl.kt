package com.example.test_app_alpha_eco.data.search

import com.example.test_app_alpha_eco.data.dto.RetrofitNetworkClient
import com.example.test_app_alpha_eco.data.model.CardDto
import com.example.test_app_alpha_eco.data.request.CardRequest
import com.example.test_app_alpha_eco.data.response.CardResponse
import com.example.test_app_alpha_eco.domain.NetworkClient
import com.example.test_app_alpha_eco.domain.models.CardData
import com.example.test_app_alpha_eco.ui.CardDataError
import com.example.test_app_alpha_eco.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(
    private val networkClient: NetworkClient
) : SearchRepository {
    override fun getCardData(number: Int): Flow<Resource<CardData>> = flow {
        val response = networkClient.doRequest(CardRequest(number))
        when (response.code) {
            RetrofitNetworkClient.INTERNET_NOT_CONNECTED -> {
                emit(Resource.Error(CardDataError.NETWORK_ERROR))
            }

            RetrofitNetworkClient.HTTP_BAD_REQUEST_CODE -> {
                emit(Resource.Error(CardDataError.BAD_REQUEST))
            }

            RetrofitNetworkClient.TOO_MANY_REQUESTS -> {
                emit(Resource.Error(CardDataError.TOO_MANY_REQUESTS))
            }

            RetrofitNetworkClient.HTTP_INTERNAL_SERVER_ERROR_CODE -> {
                emit(Resource.Error(CardDataError.SERVER_ERROR))
            }

            RetrofitNetworkClient.HTTP_OK_CODE -> {
                emit(Resource.Success(convertFromServerToAppEntity((response as CardResponse).data)))
            }

            else -> {
                emit(Resource.Error(CardDataError.UNKNOWN_ERROR))
            }
        }
    }

    private fun convertFromServerToAppEntity(cardDataToConvert: CardDto): CardData {
        return CardData(
            scheme = cardDataToConvert.scheme,
            type = cardDataToConvert.type,
            brand = cardDataToConvert.brand,
            country = cardDataToConvert.country,
            bank = cardDataToConvert.bank
        )
    }
}