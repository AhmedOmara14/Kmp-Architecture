package com.omaradev.todo_ui.home

import com.omaradev.todo_domain.models.Task

data class HomeUiState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val selectedTask: Task? = null,
    val errorMessage: String? = null
)