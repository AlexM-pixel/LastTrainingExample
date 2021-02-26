package com.example.mysamplemiddledev.db.repository

import com.example.mysamplemiddledev.model.habr_example.User
import io.reactivex.Observable
import io.reactivex.Single

interface UserFromGitHubRepository {
    fun getUsersList(): Observable<List<User>>
    fun getUserById(id: Long): Observable<User>
    fun insertUser(user: User): Single<Long?>
}