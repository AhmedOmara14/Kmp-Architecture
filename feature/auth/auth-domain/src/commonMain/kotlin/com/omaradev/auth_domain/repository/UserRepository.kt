package com.omaradev.auth_domain.repository

import com.omaradev.core_domain.model.User

interface UserRepository {
    suspend fun saveUserData(user: User, password: String)

    suspend fun getUserByName(name: String): User?

    suspend fun getUserById(id: String): User?

    suspend fun getUserPasswordByName(name: String): String?

    suspend fun getLoggedInUser(): User?

    suspend fun setLoggedInUser(user: User)

    suspend fun clearLoggedInUser()
}