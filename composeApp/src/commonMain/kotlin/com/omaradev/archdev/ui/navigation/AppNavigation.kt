package com.omaradev.archdev.ui.navigation

import androidx.compose.runtime.Composable
import com.omaradev.auth_ui.register.RegisterScreen
import com.omaradev.todo_ui.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Login) {
        composable<Login> {
            /*LoginScreen(
                onSignUp = {
                    navController.navigate(Register)
                },
                goToHomePage = {
                   // navController.navigate(Home)
                }
            )*/
            HomeScreen()
        }
        composable<Register> {
            RegisterScreen(
                navigateUp = {
                    navController.navigateUp()
                },
                goToHomePage = {
                   // navController.navigate(Home)
                }
            )
        }
    }
}