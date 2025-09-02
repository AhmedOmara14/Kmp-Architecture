package com.omaradev.todo_domain.usecase

import com.omaradev.todo_domain.models.Task
import com.omaradev.todo_domain.repositories.TodoRepository

class SaveTaskUseCase(
    private val repository: TodoRepository,
) {
    suspend operator fun invoke(task: Task) {
        repository.save(task)
    }
}
