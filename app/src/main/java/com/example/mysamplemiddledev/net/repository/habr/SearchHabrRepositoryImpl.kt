package com.example.mysamplemiddledev.net.repository.habr


import com.example.mysamplemiddledev.model.Result
import com.example.mysamplemiddledev.net.api.ApiService
import io.reactivex.Observable

class SearchHabrRepositoryImpl(private val apiService: ApiService) : SearchHabrRepository {
    override fun searchUsers(location: String, language: String): Observable<Result> {
        return apiService.search(location = location, language = language)
    }

}

