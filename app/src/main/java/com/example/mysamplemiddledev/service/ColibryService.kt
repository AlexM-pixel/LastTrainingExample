package com.example.mysamplemiddledev.service

import com.example.mysamplemiddledev.model.colibri_example.Post
import com.example.mysamplemiddledev.net.repository.colibri.ColibriRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ColibryService @Inject constructor(private val colibriRepository: ColibriRepository) {

    fun searchUser(id: Int): Observable<Post> {
        return colibriRepository.searchUser(id = id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun sendPost(post: Post) : Observable<Post> {
        return colibriRepository.sendPost(post = post)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}