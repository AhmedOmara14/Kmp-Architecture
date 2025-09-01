package com.omaradev.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.omaradev.database.dao.TodoDao
import com.omaradev.database.dao.UserDao
import com.omaradev.database.entity.TodoEntity
import com.omaradev.database.entity.UserEntity

@Database(
    version = 3,
    entities = [
        UserEntity::class,
        TodoEntity::class
    ],
    exportSchema = true
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
    abstract fun getUserDao(): UserDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}