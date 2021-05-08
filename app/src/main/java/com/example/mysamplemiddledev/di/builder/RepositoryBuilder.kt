package com.example.mysamplemiddledev.di.builder

import com.example.mysamplemiddledev.db.repository.UsersFromDbRepository
import com.example.mysamplemiddledev.db.repository.UsersFromDbRepositoryImpl
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
import dagger.Module
import dagger.Provides

@Module
class RepositoryBuilder {

    @Provides
    fun provideHabrRepository(api: ApiService): HabrRepository {
        return HabrRepositoryImpl(apiService = api)
    }

    @Provides
    fun provideUserFromGitHubRepository(userDao: UserDao): UsersFromDbRepository {
        return UsersFromDbRepositoryImpl(userDao = userDao)
    }

    @Provides
    fun provideCatFactsRepository(catsFactApi: CatsFactApi): CatFactsRepository {
        return CatFactsRepositoryImpl(catsFactApi = catsFactApi)
    }

    @Provides
    fun provideColibriRepository(networkApiService: NetworkApiService): ColibriRepository {
        return ColibriRepositoryImpl(networkApiService = networkApiService)
    }

}