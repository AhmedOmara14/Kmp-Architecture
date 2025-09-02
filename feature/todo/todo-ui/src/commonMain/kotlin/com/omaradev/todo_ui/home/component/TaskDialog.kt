package com.omaradev.todo_ui.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.benasher44.uuid.uuid4
import com.omaradev.core_ui.components.AppButton
import com.omaradev.core_ui.components.AppTextInput
import com.omaradev.todo_domain.models.Task
import kotlin.random.Random

@Composable
fun TaskDialog(
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    val colors = listOf(
        0xFFFF6B6B,
        0xFFB5C7FF,
        0xFFFFE29D,
        0xFFC6F6D5,
        0xFFFECACA
    )

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 6.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Create Task", style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(12.dp))

                AppTextInput(
                    modifier = Modifier.fillMaxWidth(),
                    defaultValue = title,
                    hint = "Title",
                    onValueChange = { title = it },
                )

                Spacer(modifier = Modifier.height(8.dp))

                AppTextInput(
                    modifier = Modifier.fillMaxWidth(),
                    defaultValue = description,
                    hint = "Description",
                    onValueChange = { description = it },
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppButton(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    value = "Save",
                    onClick = {
                        val randomColor = colors.random(Random)
                        val newTask = Task(
                            id = uuid4().toString(),
                            title = title,
                            description = description,
                            time = time,
                            backgroundColorResource = randomColor
                        )
                        onSave(newTask)
                        onDismiss()
                    },
                )
            }
        }
    }
}
