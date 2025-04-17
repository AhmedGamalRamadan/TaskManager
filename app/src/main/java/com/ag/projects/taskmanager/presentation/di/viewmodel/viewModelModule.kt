package com.ag.projects.taskmanager.presentation.di.viewmodel

import com.ag.projects.taskmanager.presentation.screen.create.CreateTaskScreenViewModel
import com.ag.projects.taskmanager.presentation.screen.home.HomeScreenViewModel
import org.koin.dsl.module

val viewModelModule = module {

    single {
        HomeScreenViewModel(
            get(),
            get(),
            get(),
            get(),
        )
    }

    single {
        CreateTaskScreenViewModel(get())
    }
}