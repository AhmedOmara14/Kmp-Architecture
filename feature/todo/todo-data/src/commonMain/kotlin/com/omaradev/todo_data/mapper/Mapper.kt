package com.omaradev.todo_data.mapper

import com.omaradev.database.entity.TodoEntity
import com.omaradev.todo_domain.models.Task

object Mapper {
    fun toDatabase(task: Task): TodoEntity {
        return TodoEntity(
            id = task.id,
            title = task.title,
            description = task.description,
            userId = task.userId ?: "",
            time = task.time,
            backgroundColorResource = task.backgroundColorResource
        )
    }

    fun fromDatabase(todoEntity: TodoEntity): Task {
        return Task(
            id = todoEntity.id,
            title = todoEntity.title,
            description = todoEntity.description,
            userId = todoEntity.userId,
            time = todoEntity.time,
            backgroundColorResource = todoEntity.backgroundColorResource
        )
    }
}