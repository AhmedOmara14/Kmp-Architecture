package com.omaradev.archdev

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.auth_domain.result.LoginResult
import com.omaradev.auth_domain.usecase.LoginUseCase
import com.omaradev.auth_domain.usecase.RegisterUseCase
import com.omaradev.core_domain.model.User
import kotlinx.coroutines.launch

class HomeViewModel(
    val loginUseCase: LoginUseCase,
    val registerUseCase: RegisterUseCase
) : ViewModel() {

    var userName by mutableStateOf("")
        private set

    var loginResult by mutableStateOf<LoginResult?>(null)
        private set

    fun saveUser(user: User) {
        viewModelScope.launch {
            registerUseCase(user.name, "password") // replace with actual password logic
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginResult = loginUseCase(username, password)
        }
    }
}
