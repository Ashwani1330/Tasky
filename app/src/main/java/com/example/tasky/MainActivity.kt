package com.example.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.tasky.screens.TaskScreen
import com.example.tasky.ui.theme.TaskyTheme
import com.example.tasky.view_models.TaskViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<TaskViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskyTheme {
                TaskScreen(viewModel)
            }
        }
    }
}