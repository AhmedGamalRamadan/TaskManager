package com.ag.projects.taskmanager.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String = "",
    val priority: Priority = Priority.MEDIUM,
//    val dueDate: Date? = null,
    val isCompleted: Boolean = false,
    val createdAt: Date = Date()
) {
   // fun dueDateAsLocalDate(): LocalDate? = dueDate?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
}

enum class Priority {
    LOW, MEDIUM, HIGH
}
enum class TaskFilter { ALL, COMPLETED, PENDING }
enum class TaskSort { PRIORITY, DUE_DATE, TITLE, CREATION_DATE }