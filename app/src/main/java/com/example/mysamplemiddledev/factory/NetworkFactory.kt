package com.example.mysamplemiddledev.factory

import com.example.mysamplemiddledev.net.api.ApiService
import com.example.mysamplemiddledev.net.api.NetworkApiService
import com.example.mysamplemiddledev.utils.Config
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkFactory {

    /**
     * Companion object to create
     * пока вместо провайдеров даггера
     */
    companion object Factory {
        fun provideHabrApi(): ApiService {
            val retrofit = Retrofit.Builder()                               // retrofit из примера:  https://habr.com/ru/post/336034/
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)
        }

        fun ProvideColibriApi(): NetworkApiService {
            val retrofit = Retrofit.Builder()                               // retrofit из примера: https://devcolibri.com/getting-started-with-retrofit-in-android/
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Config.BASE_URL_COLIBRI)
                .build()
            return retrofit.create(NetworkApiService::class.java)
        }
    }

}