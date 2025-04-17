package com.ag.projects.taskmanager.data.repository

import com.ag.projects.taskmanager.data.local.Priority
import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.data.local.TaskDatabase
import com.ag.projects.taskmanager.domain.repository.TaskLocalRepository
import kotlinx.coroutines.flow.Flow

class TaskLocalRepositoryImpl(
    private val taskDatabase: TaskDatabase
) :TaskLocalRepository{

    override suspend fun upsert(task: Task) {
        taskDatabase.taskDao().upsert(task)
    }

    override suspend fun delete(task: Task) {
        taskDatabase.taskDao().delete(task)
    }

    override fun getAllTasks(): Flow<List<Task>> {
        return taskDatabase.taskDao().getAllTasks()
    }

    override fun getTasksByCompletion(isCompleted: Boolean): Flow<List<Task>> {
        return taskDatabase.taskDao().getTasksByCompletion(isCompleted)
    }

    override fun getTasksSortedByPriority(priority: Priority): Flow<List<Task>> {
        return taskDatabase.taskDao().getTasksSortedByPriority(priority)
    }

    override fun getTasksSortedByDueDate(): Flow<List<Task>> {
        return taskDatabase.taskDao().getTasksSortedByDueDate()
    }

    override fun getTasksSortedByTitle(): Flow<List<Task>> {
        return taskDatabase.taskDao().getTasksSortedByTitle()
    }
}