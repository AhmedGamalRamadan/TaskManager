package com.ag.projects.taskmanager.presentation.di.viewmodel

import com.ag.projects.taskmanager.presentation.screen.home.HomeScreenViewModel
import org.koin.dsl.module

val viewModelModule = module {

    single {
        HomeScreenViewModel(get())
    }
}