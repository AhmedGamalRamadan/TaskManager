package com.ag.projects.taskmanager.utils

import com.ag.projects.taskmanager.data.local.Priority

sealed class TaskActionFilter(val label: String) {
    data object All : TaskActionFilter("All")
    data object Completed : TaskActionFilter("Completed")
    data object Pending : TaskActionFilter("Pending")
    data object SortByTitle : TaskActionFilter("Sort By Title")
    data object SortByDate : TaskActionFilter("Sort By Date")
    data class SortByPriority(val priority: Priority) : TaskActionFilter(
        "Sort By ${priority.name.lowercase().replaceFirstChar { it.uppercase() }}"
    )
}
