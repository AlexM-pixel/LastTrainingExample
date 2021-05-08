package com.example.mysamplemiddledev.model.cat_facts

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    val id: Long,
    @SerializedName("_v")
    val number: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("isAdmin")
    val isAdmin: Boolean,
    @SerializedName("google.accessToken")
    val googleAccessToken: String
)
