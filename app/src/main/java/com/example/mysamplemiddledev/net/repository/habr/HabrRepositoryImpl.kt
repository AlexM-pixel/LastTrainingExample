package com.example.mysamplemiddledev.net.repository.habr


import com.example.mysamplemiddledev.model.habr_example.ResponseResult
import com.example.mysamplemiddledev.net.api.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class HabrRepositoryImpl @Inject constructor(
    private val apiService: ApiService
    ) : HabrRepository {
    override fun searchUsers(language: String, type: String): Observable<ResponseResult> {
        return apiService.search(language = language, type = type)
    }
}

