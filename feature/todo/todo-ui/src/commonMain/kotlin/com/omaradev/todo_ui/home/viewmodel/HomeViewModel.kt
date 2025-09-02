package com.omaradev.todo_ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.todo_domain.models.Task
import com.omaradev.todo_domain.usecase.DeleteTaskUseCase
import com.omaradev.todo_domain.usecase.GetLoggedUserIdUseCase
import com.omaradev.todo_domain.usecase.GetTaskByIdUseCase
import com.omaradev.todo_domain.usecase.ObserveTasksByUserIdUseCase
import com.omaradev.todo_domain.usecase.SaveTaskUseCase
import com.omaradev.todo_ui.home.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTaskUseCase: GetTaskByIdUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val observeTasksByUserIdUseCase: ObserveTasksByUserIdUseCase,
    private val getLoggedUserIdUseCase: GetLoggedUserIdUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch {
            try {
                val userId = getLoggedUserIdUseCase() ?: return@launch
                observeTasks(userId)
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message) }
            }
        }
    }
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun observeTasks(userId: String) {
        viewModelScope.launch {
            observeTasksByUserIdUseCase(userId)
                .onStart { _uiState.update { it.copy(isLoading = true) } }
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
                }
                .collect { tasks ->
                    _uiState.update { it.copy(isLoading = false, tasks = tasks) }
                }
        }
    }

    fun getTaskById(taskId: String) {
        viewModelScope.launch {
            try {
                val task = getTaskUseCase(taskId)
                _uiState.update { it.copy(selectedTask = task) }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message) }
            }
        }
    }

    fun saveTask(task: Task) {
        viewModelScope.launch {
            try {
                val userId = getLoggedUserIdUseCase() ?: return@launch
                task.userId = userId
                saveTaskUseCase(task)

                observeTasks(userId)
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message) }
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                deleteTaskUseCase(task.id)
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message) }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

}