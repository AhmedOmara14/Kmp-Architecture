package com.omaradev.todo_domain.usecase

import com.omaradev.todo_domain.repositories.TodoRepository

class DeleteTaskUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(taskId: String) {
        repository.delete(taskId)
    }
}
