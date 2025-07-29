package com.omaradev.archdev

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform