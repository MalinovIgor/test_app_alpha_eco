package com.example.test_app_alpha_eco.di

import com.example.test_app_alpha_eco.ui.CardDataViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::CardDataViewModel)
}