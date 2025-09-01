package com.omaradev.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.omaradev.database.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insert(item: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    suspend fun getUserById(id: String): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE name = :name")
    suspend fun getUserByName(name: String): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE isLoggedIn = true")
    suspend fun getLoggedInUser(): UserEntity?

    @Query("UPDATE UserEntity SET isLoggedIn = false")
    suspend fun clearLoggedInUser()

    @Query("UPDATE UserEntity SET isLoggedIn = true WHERE id = :id")
    suspend fun setLoggedInUser(id: String)
}
