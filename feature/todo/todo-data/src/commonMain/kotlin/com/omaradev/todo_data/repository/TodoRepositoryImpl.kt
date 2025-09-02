package com.omaradev.todo_data.repository

import com.omaradev.database.dao.TodoDao
import com.omaradev.database.dao.UserDao
import com.omaradev.todo_data.mapper.Mapper
import com.omaradev.todo_domain.models.Task
import com.omaradev.todo_domain.repositories.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val todoDao: TodoDao,
    private val userDao: UserDao
) : TodoRepository {
    override suspend fun save(item: Task) {
        todoDao.save(
            Mapper.toDatabase(item)
        )
    }

    override suspend fun delete(id: String) {
        todoDao.delete(id)
    }

    override suspend fun getTodoById(id: String): Task {
        return Mapper.fromDatabase(todoDao.getTodoById(id))
    }

    override fun observeAllByUserId(userId: String): Flow<List<Task>> {
        return todoDao.observeAllByUserId(userId).map {
            it.map {
                Mapper.fromDatabase(it)
            }
        }
    }

    override suspend fun getSavedUserId(): String? {
        return userDao.getLoggedInUser()?.id
    }

}