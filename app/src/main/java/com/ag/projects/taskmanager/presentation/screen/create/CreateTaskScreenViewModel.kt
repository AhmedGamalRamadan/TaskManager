package com.ag.projects.taskmanager.presentation.screen.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.usecase.upsert.UpsertUseCase
import kotlinx.coroutines.launch

class CreateTaskScreenViewModel(
    private val upsertUseCase: UpsertUseCase
) : ViewModel() {

    fun upsertTask(task: Task) {
        viewModelScope.launch {
            try {
                upsertUseCase(task)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}