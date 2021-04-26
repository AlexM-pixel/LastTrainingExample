package com.example.mysamplemiddledev.db.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.mysamplemiddledev.model.habr_example.ResponseUser
import com.example.mysamplemiddledev.model.habr_example.User

@Database(entities = [User::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DB_NAME = "users.db"
    }
}