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
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    Content()
}

@Composable
fun Content(viewModel: HomeViewModel = koinViewModel()) {
    val token = remember { mutableStateOf("") }
    val count = remember { mutableStateOf(1) }

    LaunchedEffect(viewModel.token) {
        token.value = viewModel.token ?: ""
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            count.value++
            viewModel.saveToken("Token count: ${count.value}")
        }) {
            Text("Save Token")
        }
        Text("Current Token: ${token.value}")
    }

}