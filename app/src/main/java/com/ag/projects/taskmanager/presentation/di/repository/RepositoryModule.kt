package com.ag.projects.taskmanager.presentation.di.repository

import com.ag.projects.taskmanager.data.repository.TaskLocalRepositoryImpl
import com.ag.projects.taskmanager.domain.repository.TaskLocalRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<TaskLocalRepository> {
        TaskLocalRepositoryImpl(get())
    }
}