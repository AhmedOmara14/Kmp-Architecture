package com.omaradev.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val title: String,
    val time: String,
    val description: String,
    val backgroundColorResource: Long
)