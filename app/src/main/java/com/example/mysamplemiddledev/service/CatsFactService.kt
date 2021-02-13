package com.example.mysamplemiddledev.service

import com.example.mysamplemiddledev.model.cat_facts.Fact
import com.example.mysamplemiddledev.providers.NetworkFactoryProvider
import com.example.mysamplemiddledev.providers.RepositoriesProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Сервис для запросов связанных с получением фактов о котах
 */
open class CatsFactService {
    fun getFactsCat(): Observable<List<Fact>> {
        val catsFactApi = NetworkFactoryProvider.provideCatsApiFactory()
        val catFactsRepository =
            RepositoriesProvider.provideCatFactsRepository(catsFactApi = catsFactApi)
        return catFactsRepository.getFactCats()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}