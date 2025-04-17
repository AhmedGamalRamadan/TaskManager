package com.ag.projects.taskmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ag.projects.taskmanager.data.local.converters.DateConverter

@Database(
    entities = [Task::class],
    version = 1, exportSchema = false
)
@TypeConverters(
    DateConverter::class
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}