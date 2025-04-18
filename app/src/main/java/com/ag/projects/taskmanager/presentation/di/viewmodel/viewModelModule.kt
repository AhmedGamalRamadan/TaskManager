package com.ag.projects.taskmanager.presentation.di.viewmodel

import com.ag.projects.taskmanager.presentation.screen.create.CreateTaskScreenViewModel
import com.ag.projects.taskmanager.presentation.screen.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::HomeScreenViewModel)

    viewModelOf(::CreateTaskScreenViewModel)

}