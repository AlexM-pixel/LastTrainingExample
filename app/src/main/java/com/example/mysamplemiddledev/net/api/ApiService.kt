package com.example.mysamplemiddledev.net.api


import com.example.mysamplemiddledev.model.habr_example.ResponseResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun search(
        @Query("q") language: String,
        @Query("type") type: String
    ): Observable<ResponseResult>
}