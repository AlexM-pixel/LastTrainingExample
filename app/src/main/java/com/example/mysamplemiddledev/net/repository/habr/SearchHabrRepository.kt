package com.example.mysamplemiddledev.net.repository.habr


import com.example.mysamplemiddledev.model.Result
import io.reactivex.Observable

interface SearchHabrRepository {
    fun searchUsers(location: String, language: String) : Observable<Result>
}