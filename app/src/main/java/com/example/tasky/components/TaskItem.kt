package com.example.tasky.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.tasky.models.Task
import com.example.tasky.view_models.TaskViewModel

@Composable
fun TaskItem(
    viewModel: TaskViewModel,
    task: Task,
) {

    val itemAppeared = remember { mutableStateOf(false) }


    LaunchedEffect(key1 = true, block = {
        viewModel.deleteAllCompletedEvent.collect { taskList ->
            if (taskList.contains(task.id)) {
                itemAppeared.value = false
                viewModel.deleteTask(task.id)
            }
        }
    }
    )
}