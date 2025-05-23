package com.ag.projects.taskmanager.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Upsert
    suspend fun upsert(task: Task)

    @Query("DELETE FROM tasks WHERE id =:id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isCompleted = :isCompleted ORDER BY createdAt DESC")
    fun getTasksByCompletion(isCompleted: Boolean): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE priority = :priority ORDER BY createdAt DESC")
    fun getTasksSortedByPriority(priority: Priority): Flow<List<Task>>

    @Query("SELECT * FROM tasks ORDER BY createdAt ASC")
    fun getTasksSortedByDueDate(): Flow<List<Task>>

    @Query("SELECT * FROM tasks ORDER BY title ASC")
    fun getTasksSortedByTitle(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id =:id")
    suspend fun getTaskDetails(id: Int): Task
}