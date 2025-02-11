package com.example.test_app_alpha_eco.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.test_app_alpha_eco.data.dto.Api
import com.example.test_app_alpha_eco.data.dto.RetrofitNetworkClient
import com.example.test_app_alpha_eco.domain.NetworkClient
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {
    single<ConnectivityManager> {
        androidContext().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
    }

    single<Api> {
        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), get())
    }
}