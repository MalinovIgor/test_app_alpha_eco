package com.example.test_app_alpha_eco.di

import com.example.test_app_alpha_eco.data.search.SearchRepository
import com.example.test_app_alpha_eco.data.search.SearchRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<SearchRepository> {
        SearchRepositoryImpl(get())
    }
}