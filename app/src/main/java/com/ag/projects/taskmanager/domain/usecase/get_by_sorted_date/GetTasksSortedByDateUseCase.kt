package com.ag.projects.taskmanager.domain.usecase.get_by_sorted_date

import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.repository.TaskLocalRepository
import kotlinx.coroutines.flow.Flow

class GetTasksSortedByDateUseCase(
    private val taskLocalRepository: TaskLocalRepository
) {
    operator fun invoke(): Flow<List<Task>> = taskLocalRepository.getTasksSortedByDueDate()

}