package com.example.test_app_alpha_eco.data.dto

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.test_app_alpha_eco.data.Response
import com.example.test_app_alpha_eco.data.request.CardRequest
import com.example.test_app_alpha_eco.domain.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class RetrofitNetworkClient(
    private val connectivityManager: ConnectivityManager,
    private val apiService: Api
) : NetworkClient {
    override suspend fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return Response(INTERNET_NOT_CONNECTED)
        }
        return when (dto) {
            is CardRequest -> getCardData(dto)

            else -> {
                return Response().apply {
                    code = HTTP_BAD_REQUEST_CODE
                }
            }
        }
    }

    private suspend fun getCardData(request: CardRequest): Response {
        return withContext(Dispatchers.IO) {
            try {
                apiService.getCardData(request.number).apply { code = HTTP_OK_CODE }
            } catch (e: HttpException) {
                Log.e("some error in html", "$e")
                Response().apply { code = HTTP_CODE_0 }
            } catch (e: IOException) {
                Log.e("error in fetching country regions", "$e")
                Response().apply { code = HTTP_INTERNAL_SERVER_ERROR_CODE }
            }
        }
    }

    private fun isConnected(): Boolean {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            false
        }
    }

    companion object {
        const val INTERNET_NOT_CONNECTED = -1
        const val HTTP_BAD_REQUEST_CODE = 400
        const val TOO_MANY_REQUESTS = 429
        const val HTTP_OK_CODE = 200
        const val HTTP_CODE_0 = 0
        const val HTTP_INTERNAL_SERVER_ERROR_CODE = 500
    }
}