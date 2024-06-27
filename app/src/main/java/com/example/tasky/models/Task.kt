package com.example.tasky.models

import java.util.UUID

data class Task(
    val id: UUID,
    val body: String,
    val completed: Boolean,
)