package com.example.tasky.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasky.ui.theme.MEDIUM_PADDING
import com.example.tasky.ui.theme.SMALL_PADDING
import com.example.tasky.view_models.TaskViewModel

@Composable
fun AddTaskInput(
    viewModel: TaskViewModel,
    isInputVisible: MutableState<Boolean>
) {

    // focusManager allows us to clear the focus programmatically
    val focusManager = LocalFocusManager.current

    // keyboardController allows us to hide the keyboard programmatically
    val keyboardController = LocalSoftwareKeyboardController.current

    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(Unit) {
        if (isInputVisible.value) {
            focusRequester.requestFocus()
        }
    }

    // state of our new task body
    var body by remember {
        mutableStateOf("")
    }

    // states for managing errors
    var error by remember { mutableStateOf("") }
    var isErrorVisible by remember { mutableStateOf(false) }

    if (isInputVisible.value) {
        Column(modifier = Modifier.padding(MEDIUM_PADDING,SMALL_PADDING)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = body,
                onValueChange = {
                    isErrorVisible = false
                    body = it
                },
                label = { Text(text = "Enter task") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        val (hasError, errorMessage) = viewModel.addTask(body)
                        error = errorMessage
                        isErrorVisible = hasError

                        if (!hasError) {
                            body = ""
                            isInputVisible.value = false
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    }),
                isError = isErrorVisible,
            )
            if (isErrorVisible) {
                Text(text = error, color = MaterialTheme.colorScheme.error)
            }
        }
    }

}


@Preview
@Composable
fun AddTaskInputPreview() {
    AddTaskInput(viewModel = TaskViewModel(), remember { mutableStateOf(true) } )
}