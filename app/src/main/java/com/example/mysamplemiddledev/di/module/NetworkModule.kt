package com.example.mysamplemiddledev.di.module

import com.example.mysamplemiddledev.net.api.ApiService
import com.example.mysamplemiddledev.net.api.CatsFactApi
import com.example.mysamplemiddledev.net.api.NetworkApiService
import com.example.mysamplemiddledev.utils.Config
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideHabrApi(): ApiService {
        return provideRetrofitBuilder()
            .baseUrl(Config.BASE_URL)
            .client(provideOkHttpClient())
            .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCatsFactApi(): CatsFactApi {
        return provideRetrofitBuilder()
            .baseUrl(Config.BASE_URL_CAT_FACTS)
            .build().create(CatsFactApi::class.java)
    }

    @Provides
    @Singleton
    fun provideColibriApi(): NetworkApiService {
        return provideRetrofitBuilder()
            .baseUrl(Config.BASE_URL_COLIBRI)
            .build().create(NetworkApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}