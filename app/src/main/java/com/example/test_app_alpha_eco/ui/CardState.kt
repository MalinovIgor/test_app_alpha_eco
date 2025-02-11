package com.example.test_app_alpha_eco.ui

import com.example.test_app_alpha_eco.domain.models.Card

sealed interface CardState {
    data object Loading : CardState

    data class Content(
        val item: Card,
    ) : CardState

    data object TooManyRequests : CardState

    data object NetworkError : CardState

    data object BadRequest : CardState

    data object ServerError : CardState
    data object WrongInput : CardState
    data object NormalCondition : CardState

}