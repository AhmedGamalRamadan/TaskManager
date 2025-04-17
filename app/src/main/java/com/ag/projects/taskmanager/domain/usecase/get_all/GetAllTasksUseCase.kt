package com.ag.projects.taskmanager.domain.usecase.get_all

import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.repository.TaskLocalRepository
import kotlinx.coroutines.flow.Flow

class GetAllTasksUseCase(
    private val taskLocalRepository: TaskLocalRepository
) {
    suspend operator fun invoke(): Flow<List<Task>> = taskLocalRepository.getAllTasks()
}