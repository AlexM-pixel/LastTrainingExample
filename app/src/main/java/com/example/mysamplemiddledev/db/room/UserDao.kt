package com.example.mysamplemiddledev.db.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mysamplemiddledev.model.habr_example.User
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Single<Long?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(usersList: MutableList<User>): Single<List<Long>>

    @Query("SELECT * from favorite_user")
    fun getAllUsers(): Observable<List<User>>

    @Query("SELECT * from favorite_user WHERE id=:id")
    fun getById(id: Long): Observable<User>

    @Query("DELETE from favorite_user")
    fun deleteAll()
}