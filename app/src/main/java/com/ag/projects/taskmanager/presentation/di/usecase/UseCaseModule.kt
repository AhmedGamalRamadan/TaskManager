package com.ag.projects.taskmanager.presentation.di.usecase

import com.ag.projects.taskmanager.domain.usecase.delete.DeleteUseCase
import com.ag.projects.taskmanager.domain.usecase.details.GetTaskDetailsUseCase
import com.ag.projects.taskmanager.domain.usecase.get_all.GetAllTasksUseCase
import com.ag.projects.taskmanager.domain.usecase.get_by_priority.GetTasksSortedByPriority
import com.ag.projects.taskmanager.domain.usecase.get_by_sorted_date.GetTasksSortedByDateUseCase
import com.ag.projects.taskmanager.domain.usecase.get_by_sorted_title.GetTasksSortedByTitle
import com.ag.projects.taskmanager.domain.usecase.get_complete_tasks.GetCompletedTasksUseCase
import com.ag.projects.taskmanager.domain.usecase.upsert.UpsertUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {


    singleOf(::UpsertUseCase)
    singleOf(::DeleteUseCase)

    singleOf(::GetAllTasksUseCase)
    singleOf(::GetTasksSortedByPriority)
    singleOf(::GetTasksSortedByDateUseCase)

    singleOf(::GetTasksSortedByTitle)
    singleOf(::GetCompletedTasksUseCase)
    singleOf(::GetTaskDetailsUseCase)

}