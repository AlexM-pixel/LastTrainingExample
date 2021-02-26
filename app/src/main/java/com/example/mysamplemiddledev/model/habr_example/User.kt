package com.example.mysamplemiddledev.model.habr_example

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_user")
data class User(
    var login: String,
    @PrimaryKey
    var id: Long,
    var url: String,
    var html_url: String,
    var avatar_url: String,
    var followers_url: String,
    var following_url: String,
    var starred_url: String,
    var gists_url: String,
    var type: String,
    var score: Int
)
