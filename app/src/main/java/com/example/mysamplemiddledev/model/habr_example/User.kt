package com.example.mysamplemiddledev.model.habr_example

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_from_GitHub")
data class User (
    var login: String,
    @PrimaryKey
    var id: Long,
    var language: String,
    var url: String,
    var html_url: String,
    var avatar_url: String,
    var isExpanded: Boolean,
    var isSaved:Boolean,
    var type: String,
    var score: Int
)