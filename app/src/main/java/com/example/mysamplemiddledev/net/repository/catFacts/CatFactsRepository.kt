package com.example.mysamplemiddledev.net.repository.catFacts

import com.example.mysamplemiddledev.model.cat_facts.Fact
import io.reactivex.Observable

interface CatFactsRepository {
    fun getFactCats(): Observable<List<Fact>>
}