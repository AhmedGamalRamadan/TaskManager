package com.ag.projects.taskmanager.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.usecase.get_all.GetAllTasksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getAllTasksUseCase: GetAllTasksUseCase
) : ViewModel() {

    private val _tasksState = MutableStateFlow<List<Task>>(emptyList())
    val tasksState = _tasksState.asStateFlow()

    fun getAllTasks() = viewModelScope.launch {
        try {
            getAllTasksUseCase().collect { tasks ->
                _tasksState.emit(tasks)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}