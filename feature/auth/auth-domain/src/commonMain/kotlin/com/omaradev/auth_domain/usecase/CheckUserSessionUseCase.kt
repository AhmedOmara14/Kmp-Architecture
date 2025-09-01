package com.omaradev.auth_domain.usecase

import com.omaradev.auth_domain.repository.UserRepository

class CheckUserSessionUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Boolean {
        val user = userRepository.getLoggedInUser()

        return user != null
    }
}
