package com.omaradev.data.repository

import com.omaradev.auth_domain.repository.UserRepository
import com.omaradev.core_data.mapper.Mapper
import com.omaradev.core_domain.model.User
import com.omaradev.database.dao.UserDao

class UserRepositoryImpl(
    private val userDao: UserDao,
) : UserRepository {

    override suspend fun saveUserData(
        user: User, password: String
    ) {
        userDao.insert(Mapper.toDatabase(user, password, isLoggedIn = true))
    }

    override suspend fun getUserByName(name: String): User? {
        return userDao.getUserByName(name = name)?.let { Mapper.fromDatabase(userEntity = it) }
    }

    override suspend fun getUserById(id: String): User? {
        return userDao.getUserById(id = id)?.let { Mapper.fromDatabase(userEntity = it) }
    }

    override suspend fun getUserPasswordByName(name: String): String? {
        return userDao.getUserByName(name = name)?.pass
    }

    override suspend fun getLoggedInUser(): User? {
        return userDao.getLoggedInUser()?.let { Mapper.fromDatabase(userEntity = it) }
    }

    override suspend fun setLoggedInUser(user: User) {
        userDao.clearLoggedInUser()
        userDao.setLoggedInUser(user.id.value.toString())
    }

    override suspend fun clearLoggedInUser() {
        userDao.clearLoggedInUser()
    }

}