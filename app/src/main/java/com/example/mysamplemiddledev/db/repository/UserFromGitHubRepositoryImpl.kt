package com.example.mysamplemiddledev.db.repository

import com.example.mysamplemiddledev.db.room.UserDao
import com.example.mysamplemiddledev.model.habr_example.User
import io.reactivex.Observable
import io.reactivex.Single

class UserFromGitHubRepositoryImpl(private val userDao: UserDao) : UserFromGitHubRepository {
    override fun getUsersList(): Observable<List<User>> {
        return userDao.getAllUsers()
    }

    override fun getUserById(id: Long): Observable<User> {
        return userDao.getById(id = id)
    }

    override fun insertUser(user: User): Single<Long?> {
        return userDao.insertUser(user = user)
    }
}