package com.ag.projects.taskmanager.domain.usecase.delete

import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.repository.TaskLocalRepository

class DeleteUseCase(
    private val taskLocalRepository: TaskLocalRepository
) {
    suspend operator fun invoke(task: Task) = taskLocalRepository.delete(task)
}