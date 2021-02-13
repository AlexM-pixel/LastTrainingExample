package com.example.mysamplemiddledev.factory

import com.example.mysamplemiddledev.net.api.ApiService
import com.example.mysamplemiddledev.net.api.CatsFactApi
import com.example.mysamplemiddledev.net.api.NetworkApiService
import com.example.mysamplemiddledev.utils.Config
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkFactory {

    /**
     * Companion object to create
     * пока вместо провайдеров даггера
     */
    companion object Factory {
        fun provideHabrApi(): ApiService {
            val retrofit =
                Retrofit.Builder()                               // retrofit из примера:  https://habr.com/ru/post/336034/
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Config.BASE_URL)
                    .client(provideOkHttpClient())
                    .build()
            return retrofit.create(ApiService::class.java)
        }

        fun provideColibriApi(): NetworkApiService {
            val retrofit =
                Retrofit.Builder()                               // retrofit из примера: https://devcolibri.com/getting-started-with-retrofit-in-android/
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Config.BASE_URL_COLIBRI)
                    .build()
            return retrofit.create(NetworkApiService::class.java)
        }

        fun provideCatFactsApi(): CatsFactApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.BASE_URL_CAT_FACTS)
                .build()
            return retrofit.create(CatsFactApi::class.java)
        }

        private fun provideOkHttpClient(): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }
    }


}