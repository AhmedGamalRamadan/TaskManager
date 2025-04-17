package com.ag.projects.taskmanager.domain.usecase.get_complete_tasks

import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.repository.TaskLocalRepository
import kotlinx.coroutines.flow.Flow

class GetCompletedTasksUseCase(
    private val taskLocalRepository: TaskLocalRepository
) {
    suspend operator fun invoke(isCompleted: Boolean): Flow<List<Task>> =
        taskLocalRepository.getTasksByCompletion(isCompleted)

}