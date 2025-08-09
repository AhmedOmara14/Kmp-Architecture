package com.omaradev.data.repository

import com.omaradev.auth_domain.repository.UserRepository
import com.omaradev.core_data.Mapper
import com.omaradev.core_domain.model.User
import com.omaradev.database.dao.UserDao

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun saveUserData(
        user: User,
        password: String
    ) {
        userDao.insert(Mapper.toDatabase(user, password))
    }

    override suspend fun getUserByName(name: String): User {
        return Mapper.fromDatabase(userEntity = userDao.getUserByName(name = name))
    }

    override suspend fun getUserById(id: String): User {
        return Mapper.fromDatabase(userEntity = userDao.getUserById(id = id))
    }

    override suspend fun getUserPasswordByName(name: String): String {
        return userDao.getUserByName(name = name).pass
    }

}