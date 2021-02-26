package com.example.mysamplemiddledev.db.room

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mysamplemiddledev.App

import com.example.mysamplemiddledev.model.habr_example.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "users.db"


        fun getDatabase(application: Application): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    application.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
            }
            return INSTANCE!!
        }
    }
}