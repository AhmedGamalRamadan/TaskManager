package com.ag.projects.taskmanager.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.usecase.get_all.GetAllTasksUseCase
import com.ag.projects.taskmanager.domain.usecase.get_complete_tasks.GetCompletedTasksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val getCompletedTasksUseCase: GetCompletedTasksUseCase
) : ViewModel() {

    private val _tasksState = MutableStateFlow<List<Task>>(emptyList())
    val tasksState = _tasksState.asStateFlow()

    init {
        getAllTasks()
    }

    private fun getAllTasks() = viewModelScope.launch {
        try {
            getAllTasksUseCase().collect { tasks ->
                _tasksState.emit(tasks)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getCompletedTasks(isCompleted: Boolean) = viewModelScope.launch {
        try {
            getCompletedTasksUseCase(isCompleted = isCompleted).collect { tasks ->
                _tasksState.emit(tasks)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}