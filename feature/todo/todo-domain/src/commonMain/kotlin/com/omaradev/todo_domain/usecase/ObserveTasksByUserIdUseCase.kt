package com.omaradev.todo_domain.usecase

import com.omaradev.todo_domain.models.Task
import com.omaradev.todo_domain.repositories.TodoRepository
import kotlinx.coroutines.flow.Flow

class ObserveTasksByUserIdUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke(userId: String): Flow<List<Task>> {
        return repository.observeAllByUserId(userId)
    }
}
