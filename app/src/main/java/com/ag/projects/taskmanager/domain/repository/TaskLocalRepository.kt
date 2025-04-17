package com.ag.projects.taskmanager.domain.repository

import com.ag.projects.taskmanager.data.local.Priority
import com.ag.projects.taskmanager.data.local.Task
import kotlinx.coroutines.flow.Flow

interface TaskLocalRepository {

    suspend fun upsert(task: Task)

    suspend fun delete(id: Int)

    suspend fun getAllTasks(): Flow<List<Task>>

    suspend fun getTasksByCompletion(isCompleted: Boolean): Flow<List<Task>>

    suspend fun getTasksSortedByPriority(priority: Priority): Flow<List<Task>>

    suspend fun getTasksSortedByDueDate(): Flow<List<Task>>

    suspend fun getTasksSortedByTitle(): Flow<List<Task>>
}