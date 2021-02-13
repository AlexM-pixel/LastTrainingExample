package com.example.mysamplemiddledev.net.repository.catFacts

import com.example.mysamplemiddledev.model.cat_facts.Fact
import com.example.mysamplemiddledev.net.api.CatsFactApi
import io.reactivex.Observable

class CatFactsRepositoryImpl(private val catsFactApi: CatsFactApi) :
    CatFactsRepository {
    override fun getFactCats(): Observable<List<Fact>> {
        return catsFactApi.getFactCats()
    }
}