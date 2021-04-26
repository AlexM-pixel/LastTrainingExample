package com.example.mysamplemiddledev.di.builder

import com.example.mysamplemiddledev.db.repository.UsersFromDbRepository
import com.example.mysamplemiddledev.net.repository.catFacts.CatFactsRepository
import com.example.mysamplemiddledev.net.repository.colibri.ColibriRepository
import com.example.mysamplemiddledev.service.CatsFactService
import com.example.mysamplemiddledev.service.ColibryService
import com.example.mysamplemiddledev.service.UserGitHubService
import dagger.Module
import dagger.Provides

@Module
class ServiceBuilder {
    @Provides
    fun provideUserGitHubService(dbRepository: UsersFromDbRepository): UserGitHubService {
        return UserGitHubService(dbRepository = dbRepository)
    }

    @Provides
    fun provideCatsFactService(catFactsRepository: CatFactsRepository): CatsFactService {
        return CatsFactService(catFactsRepository = catFactsRepository)
    }

    @Provides
    fun provideColibriService(colibriRepository: ColibriRepository): ColibryService {
        return ColibryService(colibriRepository = colibriRepository)
    }
}