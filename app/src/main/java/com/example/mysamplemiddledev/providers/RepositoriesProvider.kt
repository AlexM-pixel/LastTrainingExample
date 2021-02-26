package com.example.mysamplemiddledev.providers

import com.example.mysamplemiddledev.db.repository.UserFromGitHubRepository
import com.example.mysamplemiddledev.db.repository.UserFromGitHubRepositoryImpl
import com.example.mysamplemiddledev.db.room.UserDao
import com.example.mysamplemiddledev.net.api.ApiService
import com.example.mysamplemiddledev.net.api.CatsFactApi
import com.example.mysamplemiddledev.net.api.NetworkApiService
import com.example.mysamplemiddledev.net.repository.catFacts.CatFactsRepository
import com.example.mysamplemiddledev.net.repository.catFacts.CatFactsRepositoryImpl
import com.example.mysamplemiddledev.net.repository.colibri.ColibriRepository
import com.example.mysamplemiddledev.net.repository.colibri.ColibriRepositoryImpl
import com.example.mysamplemiddledev.net.repository.habr.HabrRepository
import com.example.mysamplemiddledev.net.repository.habr.HabrRepositoryImpl

object RepositoriesProvider {
    fun provideHabrRepository(api: ApiService): HabrRepository {
        return HabrRepositoryImpl(apiService = api)
    }

    fun provideColibriRepository(networkApiService: NetworkApiService): ColibriRepository {
        return ColibriRepositoryImpl(networkApiService = networkApiService)
    }

    fun provideCatFactsRepository(catsFactApi: CatsFactApi): CatFactsRepository {
        return CatFactsRepositoryImpl(catsFactApi = catsFactApi)
    }

    fun provideGitHubRepository(userDao: UserDao): UserFromGitHubRepository {
        return UserFromGitHubRepositoryImpl(userDao)
    }
}