package com.example.tasky.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasky.models.Task
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.UUID

class TaskViewModel: ViewModel() {
    var taskList by mutableStateOf<List<Task>>(listOf())
        private set

    private val _deleteAllCompletedEvent = MutableSharedFlow<List<UUID>>()
    val deleteAllCompletedEvent = _deleteAllCompletedEvent.asSharedFlow()

    fun addTask(body: String): Pair<Boolean, String> {

        // blank task check
        if (body.isBlank()) {
            return true to "Cannot add a blank task"
        }

        // duplicate task check
        if (taskList.any {
            it.body == body
        }) {
            return true to "That task already exists"
        }

        // add task
        taskList = taskList + Task(UUID.randomUUID(), body, completed = false)
        return false to ""
    }

    fun toggleTaskCompleted(task: Task) {
        taskList = taskList.map {
            if (it == task) it.copy(completed = !it.completed) else it
        }
    }

    fun deleteCompletedTasks() {
        val newTaskList = taskList.filter {
            it.completed
        }.map {
            it.id
        }
        viewModelScope.launch {
            _deleteAllCompletedEvent.emit(newTaskList)
        }
    }

    fun deleteTask(taskId: UUID) {
        taskList = taskList.filter {
            it.id != taskId
        }
    }
}