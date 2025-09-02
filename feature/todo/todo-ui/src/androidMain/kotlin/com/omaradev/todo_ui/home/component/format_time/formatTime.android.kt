package com.omaradev.todo_ui.home.component.format_time

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

actual fun formatTime(epochMillis: Long): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(Date(epochMillis))
}