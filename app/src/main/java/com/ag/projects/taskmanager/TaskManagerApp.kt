package com.ag.projects.taskmanager

import android.app.Application
import com.ag.projects.taskmanager.presentation.di.local.localModule
import com.ag.projects.taskmanager.presentation.di.repository.repositoryModule
import com.ag.projects.taskmanager.presentation.di.usecase.useCaseModule
import com.ag.projects.taskmanager.presentation.di.viewmodel.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TaskManagerApp :Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TaskManagerApp)
            modules(
                localModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}