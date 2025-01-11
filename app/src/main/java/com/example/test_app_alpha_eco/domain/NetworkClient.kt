package com.example.test_app_alpha_eco.domain

import com.example.test_app_alpha_eco.data.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}