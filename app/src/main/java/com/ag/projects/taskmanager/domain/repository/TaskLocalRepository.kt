package com.ag.projects.taskmanager.domain.repository

import com.ag.projects.taskmanager.data.local.Priority
import com.ag.projects.taskmanager.data.local.Task
import kotlinx.coroutines.flow.Flow

interface TaskLocalRepository {

    suspend fun upsert(task: Task)

    suspend fun delete(id: Int)

     fun getAllTasks(): Flow<List<Task>>

     fun getTasksByCompletion(isCompleted: Boolean): Flow<List<Task>>

     fun getTasksSortedByPriority(priority: Priority): Flow<List<Task>>

     fun getTasksSortedByDueDate(): Flow<List<Task>>

     fun getTasksSortedByTitle(): Flow<List<Task>>
}