package com.example.mysamplemiddledev.service

import com.example.mysamplemiddledev.db.repository.UserFromGitHubRepository
import com.example.mysamplemiddledev.model.habr_example.ResponseUser
import com.example.mysamplemiddledev.model.habr_example.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * service for working with users added from GitHub
 */
class UserGitHubService(private val gitHubRepository: UserFromGitHubRepository) {

    fun getListUsers(): Observable<List<User>> {
        return gitHubRepository.getUsersList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertUser(list: MutableList<User>): Single<List<Long>> {
        return gitHubRepository.insertUser(list = list)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}