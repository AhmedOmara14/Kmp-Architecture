package com.omaradev.todo_domain.repositories

import com.omaradev.todo_domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun save(item: Task)
    suspend fun delete(id: String)
    suspend fun getTodoById(id: String): Task
    fun observeAllByUserId(userId: String): Flow<List<Task>>
    suspend fun getSavedUserId(): String?
}