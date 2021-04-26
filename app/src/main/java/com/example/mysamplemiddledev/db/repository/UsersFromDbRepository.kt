package com.example.mysamplemiddledev.db.repository

import com.example.mysamplemiddledev.model.habr_example.User
import io.reactivex.Observable
import io.reactivex.Single

interface UsersFromDbRepository {
    fun getUsersList(): Observable<List<User>>
    fun getUserById(id: Long): Observable<User>
    fun insertUser(list: MutableList<User>): Single<List<Long>>
}