package com.example.tasky.screens

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasky.components.AddTaskInput
import com.example.tasky.components.TaskAppTopBar
import com.example.tasky.components.TaskCard
import com.example.tasky.components.TaskItem
import com.example.tasky.ui.theme.TaskyTheme
import com.example.tasky.ui.theme.backgroundColor
import com.example.tasky.utils.deleteAllTasksDialog
import com.example.tasky.view_models.TaskViewModel

@Composable
fun TaskScreen(viewModel: TaskViewModel) {

    val isInputVisible = remember {
        mutableStateOf(false)
    }

    val deleteAllTasksDialog = remember {
        mutableStateOf(false)
    }.deleteAllTasksDialog(viewModel = viewModel)

    val rotate by animateFloatAsState(
        if (isInputVisible.value) 45f else 0f, label = ""
    )

    Scaffold(
        topBar = {
            TaskAppTopBar(deleteAllTasksDialog)
        },
        floatingActionButton = {
            IconButton(
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
                ,onClick = {
                    isInputVisible.value = !isInputVisible.value
                },
            ) {
                Icon(modifier = Modifier.rotate(rotate),
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.backgroundColor)
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding(),
        ) {
            AddTaskInput(viewModel, isInputVisible)
            // this is where we add our task list
            LazyColumn(
                modifier = Modifier.weight(1f),
                content = {
                    items(items = viewModel.taskList, key = {
                        it.id
                    }) { task ->
                        TaskCard(task, viewModel::toggleTaskCompleted)
                        TaskItem(viewModel = viewModel, task = task)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun TaskScreenPreview() {
    TaskyTheme {
        TaskScreen(viewModel = TaskViewModel())
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkTaskScreenPreview() {
    TaskyTheme {
        TaskScreen(viewModel = TaskViewModel())
    }
}