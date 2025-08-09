package com.omaradev.core_data.repository

import com.omaradev.core_data.mapper.Mapper
import com.omaradev.core_domain.model.UserId
import com.omaradev.core_domain.repository.AuthorizedUserRepository
import com.omaradev.database.dao.UserDao
import com.omaradev.preferences.IPreferences

class AuthorizedUserRepositoryImplementation(
    private val iPreferences: IPreferences,
    private val userDao: UserDao
) : AuthorizedUserRepository {
    override suspend fun userIsAuthorized(): Boolean {
        return iPreferences.getBoolean(AUTHORIZED_USER_ID)
    }

    override suspend fun saveUserId(id: UserId) {
        iPreferences.saveString(AUTHORIZED_USER_ID, id.value.toString())
    }

    override suspend fun getSavedUserId(): UserId? {
        val userId = iPreferences.getString(AUTHORIZED_USER_ID)
        val user = userId?.let { userDao.getUserById(it) }
        return user?.let { Mapper.fromDatabase(user).id }
    }

    override fun logout() {
        iPreferences.clear()
    }

    companion object {
        private const val AUTHORIZED_USER_ID = "AUTHORIZED_USER_ID"
    }
}