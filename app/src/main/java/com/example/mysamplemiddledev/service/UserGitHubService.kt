package com.example.mysamplemiddledev.service

import com.example.mysamplemiddledev.db.repository.UsersFromDbRepository
import com.example.mysamplemiddledev.model.habr_example.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * service for working with users added from GitHub
 */
class UserGitHubService @Inject constructor(private val dbRepository: UsersFromDbRepository) {

    fun getListUsers(): Observable<List<User>> {
        return dbRepository.getUsersList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertUser(list: MutableList<User>): Single<List<Long>> {
        return dbRepository.insertUser(list = list)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}