package com.example.mysamplemiddledev.providers

import com.example.mysamplemiddledev.factory.NetworkFactory
import com.example.mysamplemiddledev.net.api.ApiService
import com.example.mysamplemiddledev.net.api.NetworkApiService

object NetworkFactoryProvider {
    fun provideHubrApiFactory() : ApiService{
        return NetworkFactory.provideHabrApi()
    }
    fun provideColibriApiFactory() : NetworkApiService {
        return NetworkFactory.ProvideColibriApi()
    }
}