package com.example.test_app_alpha_eco.di

import com.example.test_app_alpha_eco.domain.api.SearchInteractor
import com.example.test_app_alpha_eco.domain.api.SearchInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    factory<SearchInteractor> {
        SearchInteractorImpl(get())
    }
}