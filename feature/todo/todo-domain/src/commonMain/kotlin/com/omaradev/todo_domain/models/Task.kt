package com.omaradev.todo_domain.models

data class Task(
    val id: String,
    var userId: String? = null,
    val title: String,
    var time: String,
    val description: String,
    val backgroundColorResource: Long
)
