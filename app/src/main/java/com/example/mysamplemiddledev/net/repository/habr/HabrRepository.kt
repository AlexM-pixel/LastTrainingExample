package com.example.mysamplemiddledev.net.repository.habr


import com.example.mysamplemiddledev.model.habr_example.Result
import io.reactivex.Observable

interface HabrRepository {
    fun searchUsers(location: String, type: String) : Observable<Result>
}