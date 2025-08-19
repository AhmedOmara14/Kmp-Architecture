package com.omaradev.todo_domain.models

data class TimelineItem(
    val title: String,
    val time: String,
    val description: String,
    val backgroundColorResource: Long
)
