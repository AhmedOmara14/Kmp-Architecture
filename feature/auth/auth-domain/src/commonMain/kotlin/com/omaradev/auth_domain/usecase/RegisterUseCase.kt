package com.omaradev.auth_domain.usecase

import com.omaradev.auth_domain.repository.UserRepository
import com.omaradev.auth_domain.result.LoginResult
import com.omaradev.auth_domain.result.RegistrationResult
import com.omaradev.core_domain.model.User


class RegisterUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String, password: String): RegistrationResult {
        val user = userRepository.getUserByName(username)
        user?.let {
            return RegistrationResult.USER_ALREADY_REGISTERED
        }?:run {
            userRepository.saveUserData(User(username), password)
            userRepository.setLoggedInUser(User(username))
            return RegistrationResult.SUCCESS
        }
    }
}