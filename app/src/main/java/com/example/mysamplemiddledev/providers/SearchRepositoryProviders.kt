package com.example.mysamplemiddledev.providers

import com.example.mysamplemiddledev.net.api.ApiService
import com.example.mysamplemiddledev.net.api.NetworkApiService
import com.example.mysamplemiddledev.net.repository.colibri.ColibriRepository
import com.example.mysamplemiddledev.net.repository.colibri.ColibriRepositoryImpl
import com.example.mysamplemiddledev.net.repository.habr.SearchHabrRepository
import com.example.mysamplemiddledev.net.repository.habr.SearchHabrRepositoryImpl

object SearchRepositoryProviders {
    fun provideHabrRepository(api: ApiService): SearchHabrRepository {
        return SearchHabrRepositoryImpl(apiService = api)
    }

    fun provideColibriRepository(networkApiService: NetworkApiService): ColibriRepository {
        return ColibriRepositoryImpl(networkApiService = networkApiService)
    }
}