package com.ag.projects.taskmanager.utils

import com.ag.projects.taskmanager.data.local.Task

data class TaskState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
