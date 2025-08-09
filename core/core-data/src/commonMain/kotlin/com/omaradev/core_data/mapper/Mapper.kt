package com.omaradev.core_data.mapper

import com.benasher44.uuid.uuidFrom
import com.omaradev.core_domain.model.User
import com.omaradev.core_domain.model.UserId
import com.omaradev.database.entity.UserEntity

object Mapper {
    fun toDatabase(user: User, password: String): UserEntity {
        return UserEntity(
            name = user.name,
            pass = password,
            id = user.id.value.toString()
        )
    }

    fun fromDatabase(userEntity: UserEntity): User {
        return User(
            name = userEntity.name,
            id = UserId(uuidFrom(userEntity.id))
        )
    }
}