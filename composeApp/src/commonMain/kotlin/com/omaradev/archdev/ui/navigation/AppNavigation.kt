package com.omaradev.archdev.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omaradev.auth_ui.login.LoginScreen
import com.omaradev.auth_ui.register.RegisterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                onSignUp = {
                    navController.navigate(Register)
                }
            )
        }
        composable<Register> {
            RegisterScreen()
        }

    }
}