package com.ag.projects.taskmanager.domain.usecase.details

import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.repository.TaskLocalRepository

class GetTaskDetailsUseCase(
    private val taskLocalRepository: TaskLocalRepository
) {

    suspend operator fun invoke(id: Int): Task =
        taskLocalRepository.getTaskDetails(id)
}