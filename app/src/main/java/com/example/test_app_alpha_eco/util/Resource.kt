package com.example.test_app_alpha_eco.util

import com.example.test_app_alpha_eco.ui.CardDataError

sealed interface Resource<T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error<T>(val message: CardDataError) : Resource<T>
}
