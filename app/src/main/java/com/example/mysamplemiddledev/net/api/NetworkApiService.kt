package com.example.mysamplemiddledev.net.api

import com.example.mysamplemiddledev.model.Post
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface NetworkApiService {
    @GET("/posts/{id}")
    fun getPostWithID(@Path("id") id: Int): Observable<Post>

    @POST("/posts")
    fun setPost(@Body data:Post):Observable<Post>
}