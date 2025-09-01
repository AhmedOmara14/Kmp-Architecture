package com.omaradev.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val pass: String,
    val isLoggedIn: Boolean = false
)
