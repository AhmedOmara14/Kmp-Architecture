package com.omaradev.core_domain.repository

import com.omaradev.core_domain.model.UserId

interface AuthorizedUserRepository {

    suspend fun userIsAuthorized(): Boolean

    suspend fun saveUserId(id: UserId)

    suspend fun getSavedUserId(): UserId?

    fun logout()

}