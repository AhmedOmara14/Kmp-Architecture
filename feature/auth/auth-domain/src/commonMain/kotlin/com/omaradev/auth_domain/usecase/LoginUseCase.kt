package com.omaradev.auth_domain.usecase

import com.omaradev.auth_domain.repository.UserRepository
import com.omaradev.auth_domain.result.LoginResult


class LoginUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String, password: String): LoginResult {
        val user = userRepository.getUserByName(username)
        val existingUserPassword = userRepository.getUserPasswordByName(name = username)

        return if (existingUserPassword == password && user?.name == username) {
            userRepository.setLoggedInUser(user)
            LoginResult.SUCCESS
        } else {
            LoginResult.FAILURE
        }
    }
}