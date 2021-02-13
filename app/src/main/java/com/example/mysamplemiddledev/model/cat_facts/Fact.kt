package com.example.mysamplemiddledev.model.cat_facts

import com.example.mysamplemiddledev.model.cat_facts.User
import com.google.gson.annotations.SerializedName
import java.util.*

data class Fact(
@SerializedName("status")
val status:Status,
    @SerializedName("_id")
    val id: String,
    @SerializedName("__v")
    val numb: Int,
    @SerializedName("user")
    val user: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("updatedAt")
    val updateDate: Date,
    @SerializedName("sendDate")
    val sendDate: Date,
    @SerializedName("deleted")
    val deleted: Boolean,
    @SerializedName("source")
    val source: String,
    @SerializedName("type")
    val type: String,
)
