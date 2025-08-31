package com.omaradev.auth_ui.navigation

import cafe.adriel.voyager.core.screen.Screen

interface AuthNavigation {
    fun login(): Screen
    fun navigateToHome(): Screen
}