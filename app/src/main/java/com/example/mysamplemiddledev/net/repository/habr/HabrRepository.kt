package com.example.mysamplemiddledev.net.repository.habr


import com.example.mysamplemiddledev.model.habr_example.ResponseResult
import io.reactivex.Observable

interface HabrRepository {
    fun searchUsers(language: String, type: String) : Observable<ResponseResult>
}