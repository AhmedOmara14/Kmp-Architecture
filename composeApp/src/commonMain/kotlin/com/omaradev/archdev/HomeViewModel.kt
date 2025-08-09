package com.omaradev.archdev

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.auth_domain.repository.UserRepository
import com.omaradev.core_domain.model.User
import kotlinx.coroutines.launch

class HomeViewModel(
    val userRepository: UserRepository
) : ViewModel() {

    var userName by mutableStateOf("")
        private set

    fun saveUser(user: User) {
        viewModelScope.launch {
            userRepository.saveUserData(user, password = "123")
            userName = userRepository.getUserByName("Ahmed").id.toString()
        }
    }

}