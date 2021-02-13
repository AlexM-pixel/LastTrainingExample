package com.example.mysamplemiddledev.net.api

import com.example.mysamplemiddledev.model.cat_facts.Fact
import io.reactivex.Observable
import retrofit2.http.GET

interface CatsFactApi {
    @GET("/facts")
    fun getFactCats():Observable<List<Fact>>
}