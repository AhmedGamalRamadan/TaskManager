package com.ag.projects.taskmanager.domain.usecase.upsert

import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.repository.TaskLocalRepository

class UpsertUseCase(
    private val taskLocalRepository: TaskLocalRepository
) {
    suspend operator fun invoke(task: Task) = taskLocalRepository.upsert(task)
}