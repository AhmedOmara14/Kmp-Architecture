package com.omaradev.archdev

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.omaradev.core_domain.model.User
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    Content()
}

@Composable
fun Content(viewModel: HomeViewModel = koinViewModel()) {
    val token = remember { mutableStateOf("") }

    LaunchedEffect(viewModel.userName) {
        token.value = viewModel.userName
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            viewModel.saveUser(
                User(
                    name = "Ahmed",
                )
            )
        }) {
            Text("Save User")
        }
        Text("Current User: ${token.value}")
    }

}