package com.example.tasky.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasky.ui.theme.TaskyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskAppTopBar(
    deleteAllTasksDialog: MutableState<Boolean>,
) {
    TopAppBar(
        title = {
            Text(text = "My Task App")
        },
        actions = {
            IconButton(onClick = {
                deleteAllTasksDialog.value = true
            }) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete Icon",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TaskAppTopBarPreview() {
    val mutableBoolean = remember {
        mutableStateOf(false)
    }
    TaskyTheme {
        TaskAppTopBar(
            deleteAllTasksDialog = mutableBoolean,
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkTaskAppTopBarPreview() {
    val mutableBoolean = remember {
        mutableStateOf(false)
    }
    TaskyTheme {
        TaskAppTopBar(
            deleteAllTasksDialog = mutableBoolean,
        )
    }
}