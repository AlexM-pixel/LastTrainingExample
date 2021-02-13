package com.example.mysamplemiddledev.net.repository.habr


import com.example.mysamplemiddledev.model.habr_example.Result
import com.example.mysamplemiddledev.net.api.ApiService
import io.reactivex.Observable

class HabrRepositoryImpl(private val apiService: ApiService) : HabrRepository {
    override fun searchUsers(language: String, type: String): Observable<Result> {
        return apiService.search(language = language, type = type)
    }

}

