package com.omaradev.todo_ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.omaradev.core_ui.components.AppLabelText
import com.omaradev.core_ui.theme.Black
import com.omaradev.core_ui.theme.ColorPrimary
import com.omaradev.core_ui.theme.WhiteColor
import com.omaradev.todo_domain.models.Task
import com.omaradev.todo_ui.home.component.TaskDialog
import com.omaradev.todo_ui.home.component.TimelineCard
import com.omaradev.todo_ui.home.component.format_time.currentTimeMillis
import com.omaradev.todo_ui.home.component.format_time.formatTime
import com.omaradev.todo_ui.home.viewmodel.HomeViewModel
import com.omaradev.todo_ui.resources.Res
import com.omaradev.todo_ui.resources.add_new_task
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


class HomeScreen(
    val logout: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        HomeScreenContent(
            logout = logout
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreenContent(
    logout: () -> Unit,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val navigator = LocalNavigator.currentOrThrow

    var showDialog by remember { mutableStateOf(false) }
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }
    val uiState by homeViewModel.uiState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = ColorPrimary, shape = RoundedCornerShape(32.dp)
                    )
                    .clickable {
                        showDialog = true
                    }
            ) {
                Row(
                    modifier = Modifier
                        .background(ColorPrimary)
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = WhiteColor
                    )
                    AppLabelText(
                        text = stringResource(Res.string.add_new_task),
                        color = WhiteColor,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { logout() },
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = null,
                tint = Black
            )
        }

        LazyColumn {
            itemsIndexed(uiState.tasks) { index, item ->
                TimelineCard(
                    item = item,
                    ifFirst = index == 0,
                    isLast = index == uiState.tasks.lastIndex
                )
            }
        }
    }

    if (showDialog) {
        TaskDialog(
            onDismiss = { showDialog = false },
            onSave = { newTask ->
                homeViewModel.saveTask(newTask.apply {
                    time = formatTime(currentTimeMillis())
                })
            }
        )
    }
}
