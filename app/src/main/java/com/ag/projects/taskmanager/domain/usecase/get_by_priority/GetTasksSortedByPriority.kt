package com.ag.projects.taskmanager.domain.usecase.get_by_priority

import com.ag.projects.taskmanager.data.local.Priority
import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.repository.TaskLocalRepository
import kotlinx.coroutines.flow.Flow

class GetTasksSortedByPriority(
    private val taskLocalRepository: TaskLocalRepository
) {
   suspend operator fun invoke(priority: Priority): Flow<List<Task>> =
        taskLocalRepository.getTasksSortedByPriority(priority)

}