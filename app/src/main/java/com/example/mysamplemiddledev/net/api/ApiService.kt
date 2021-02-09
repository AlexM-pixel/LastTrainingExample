package com.example.mysamplemiddledev.net.api


import com.example.mysamplemiddledev.model.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun search(
        @Query("location") location: String,
        @Query("q") language: String,
    ): Observable<Result>
}