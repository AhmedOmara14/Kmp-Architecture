package com.omaradev.todo_domain.usecase

import com.omaradev.todo_domain.repositories.TodoRepository

class GetLoggedUserIdUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(): String? {
        return todoRepository.getSavedUserId()
    }
}