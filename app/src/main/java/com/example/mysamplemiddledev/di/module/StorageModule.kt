package com.example.mysamplemiddledev.di.module

import android.content.Context
import androidx.room.Room
import com.example.mysamplemiddledev.db.room.AppDatabase
import com.example.mysamplemiddledev.db.room.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {
    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
   @Singleton
   @Provides
   fun provideUserDao(db:AppDatabase): UserDao {
      return db.userDao()
   }
}