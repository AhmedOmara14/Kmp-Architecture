package com.omaradev.auth_domain.usecase

import com.omaradev.auth_domain.repository.UserRepository

class ClearUserSessionUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() {
        userRepository.clearLoggedInUser()
    }
}
