package com.ag.projects.taskmanager.presentation.di.viewmodel

import com.ag.projects.taskmanager.presentation.screen.create.CreateTaskScreenViewModel
import com.ag.projects.taskmanager.presentation.screen.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeScreenViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }

    viewModel {
        CreateTaskScreenViewModel(get())
    }
}