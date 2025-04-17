package com.ag.projects.taskmanager.domain.usecase.delete

import com.ag.projects.taskmanager.domain.repository.TaskLocalRepository

class DeleteUseCase(
    private val taskLocalRepository: TaskLocalRepository
) {
    suspend operator fun invoke(id:Int) = taskLocalRepository.delete(id)
}