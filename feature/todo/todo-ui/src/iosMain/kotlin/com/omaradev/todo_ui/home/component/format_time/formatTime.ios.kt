package com.omaradev.todo_ui.home.component.format_time

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.dateWithTimeIntervalSince1970

actual fun formatTime(epochMillis: Long): String {
    val date = NSDate.dateWithTimeIntervalSince1970(epochMillis / 1000.0)
    val formatter = NSDateFormatter()
    formatter.dateFormat = "hh:mm a"
    return formatter.stringFromDate(date)
}