package com.example.test_app_alpha_eco.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_app_alpha_eco.domain.api.SearchInteractor
import com.example.test_app_alpha_eco.domain.models.Card
import com.example.test_app_alpha_eco.util.Resource
import kotlinx.coroutines.launch

class CardDataViewModel(private val interactor: SearchInteractor) : ViewModel() {
    private var currentCard: Card? = null
    private val cardScreenStateLiveData = MutableLiveData<CardState>()
    val getCardScreenStateLiveData: LiveData<CardState> = cardScreenStateLiveData
    var currentCardNumber: String? = null

    fun getCardResources(number: String) {
        renderState(CardState.Loading)
        setCardNumber(number)
        if (number.isNotEmpty()) {
            viewModelScope.launch {
                interactor.getCardData(number).collect { card ->
                    processResult(card)
                }
            }
        }
    }

    fun setCardNumber(cardNumber: String?) {
        if ((cardNumber!!.length == 6 || cardNumber.length == 8)) {
            renderState(CardState.NormalCondition)
            currentCardNumber = cardNumber
        } else {
            renderErrorState(CardDataError.WRONG_INPUT)
            clearCurrentCardNumber()
        }

    }

    private fun processResult(resource: Resource<Card>) {
        when (resource) {
            is Resource.Success -> {
                currentCard = resource.data
                renderState(CardState.Content(currentCard!!))
            }

            is Resource.Error -> {
                renderErrorState(resource.message)
            }
        }
    }

    private fun renderErrorState(error: CardDataError) {
        when (error) {
            CardDataError.NETWORK_ERROR -> {
                renderState(CardState.NetworkError)
            }

            CardDataError.BAD_REQUEST -> {
                renderState(CardState.BadRequest)
            }

            CardDataError.TOO_MANY_REQUESTS -> {
                renderState(CardState.TooManyRequests)
            }

            CardDataError.UNKNOWN_ERROR, CardDataError.SERVER_ERROR -> {
                renderState(CardState.ServerError)
            }

            CardDataError.WRONG_INPUT ->
                renderState(CardState.WrongInput)

            CardDataError.NORMAL_CONDITION -> renderState(CardState.NormalCondition)
        }
    }

    private fun renderState(state: CardState) {
        cardScreenStateLiveData.postValue(state)
    }

    fun clearCurrentCardNumber() {
        currentCardNumber = ""
    }
}