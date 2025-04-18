package com.ag.projects.taskmanager.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.usecase.delete.DeleteUseCase
import com.ag.projects.taskmanager.domain.usecase.details.GetTaskDetailsUseCase
import com.ag.projects.taskmanager.domain.usecase.upsert.UpsertUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskDetailsScreenViewModel(
    private val getTaskDetailsUseCase: GetTaskDetailsUseCase,
    private val upsertUseCase: UpsertUseCase,
    private val deleteUseCase: DeleteUseCase
) : ViewModel() {


    private val _task = MutableStateFlow<Task?>(null)
    val task = _task.asStateFlow()

    fun getTaskDetails(id: Int) {
        viewModelScope.launch {
            try {
                _task.update {
                    getTaskDetailsUseCase.invoke(id)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun upsertTask(task: Task) {
        viewModelScope.launch {
            try {
                upsertUseCase(task)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            try {
                deleteUseCase(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}