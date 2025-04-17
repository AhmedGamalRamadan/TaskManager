package com.ag.projects.taskmanager.presentation.di.local

import androidx.room.Room
import com.ag.projects.taskmanager.data.local.TaskDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {

    single {

        Room.databaseBuilder(
            androidApplication(),
            TaskDatabase::class.java,
            "tasks.db"
        ).build()
    }
}