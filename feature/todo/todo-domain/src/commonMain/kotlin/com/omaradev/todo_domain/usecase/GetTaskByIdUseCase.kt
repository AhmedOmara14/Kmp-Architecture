package com.omaradev.todo_domain.usecase

import com.omaradev.todo_domain.models.Task
import com.omaradev.todo_domain.repositories.TodoRepository

class GetTaskByIdUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(taskId: String): Task {
        return repository.getTodoById(taskId)
    }
}
