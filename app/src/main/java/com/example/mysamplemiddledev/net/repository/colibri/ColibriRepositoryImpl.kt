package com.example.mysamplemiddledev.net.repository.colibri

import com.example.mysamplemiddledev.model.colibri_example.Post
import com.example.mysamplemiddledev.net.api.NetworkApiService
import io.reactivex.Observable
import javax.inject.Inject

class ColibriRepositoryImpl @Inject constructor(private val networkApiService: NetworkApiService) :ColibriRepository {
    override fun searchUser(id: Int): Observable<Post> {
        return networkApiService.getPostWithID(id = id)
    }

    override fun sendPost(post: Post): Observable<Post> {
        return networkApiService.setPost(data = post)
    }
}