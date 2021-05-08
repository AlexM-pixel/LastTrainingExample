package com.example.mysamplemiddledev.net.repository.colibri

import com.example.mysamplemiddledev.model.colibri_example.Post
import io.reactivex.Observable

interface ColibriRepository {
    fun searchUser(id: Int) : Observable<Post>
    fun sendPost(post: Post) :Observable<Post>
}