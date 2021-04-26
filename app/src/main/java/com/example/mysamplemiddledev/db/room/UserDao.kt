package com.example.mysamplemiddledev.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mysamplemiddledev.model.habr_example.ResponseUser
import com.example.mysamplemiddledev.model.habr_example.User
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(responseUser: User): Single<Long?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(usersList: MutableList<User>): Single<List<Long>>

    @Query("SELECT * from users_from_GitHub")
    fun getAllUsers(): Observable<List<User>>

    @Query("SELECT * from users_from_GitHub WHERE id=:id")
    fun getById(id: Long): Observable<User>

    @Query("DELETE from users_from_GitHub")
    fun deleteAll()
}