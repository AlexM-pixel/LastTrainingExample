package com.example.mysamplemiddledev.service

import com.example.mysamplemiddledev.model.cat_facts.Fact
import com.example.mysamplemiddledev.net.repository.catFacts.CatFactsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Сервис для запросов связанных с получением фактов о котах
 */
open class CatsFactService @Inject constructor(private val catFactsRepository: CatFactsRepository) {
    fun getFactsCat(): Observable<List<Fact>> {
        return catFactsRepository.getFactCats()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}