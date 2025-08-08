package com.omaradev.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    return Room.databaseBuilder(context, AppDatabase::class.java, "app.db")
        .fallbackToDestructiveMigration()
}
