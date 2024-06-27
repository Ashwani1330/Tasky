package com.example.tasky.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasky.view_models.TaskViewModel

@Composable
fun MutableState<Boolean>.deleteAllTasksDialog(
    viewModel: TaskViewModel,
): MutableState<Boolean> {
    if (this.value) {
        AlertDialog(onDismissRequest = { this.value = false },
        title = {
            Text(text = "Are you sure you want to delete all completed tasks?")
        },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                    ,onClick = {
                        viewModel.deleteCompletedTasks()
                        this.value = false
                    }) {
                    Text(text = "Yes")
            }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        this.value = false
                    }) {
                    Text(text = "No")
                }
            },
        )
    }
    return this
}