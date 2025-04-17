package com.ag.projects.taskmanager.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.repository.TaskLocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val taskLocalRepository: TaskLocalRepository
) : ViewModel() {

    private val _tasksState = MutableStateFlow<List<Task>>(emptyList())
    val tasksState = _tasksState.asStateFlow()

    fun getAllTasks() = viewModelScope.launch {
        try {
            taskLocalRepository.getAllTasks().collect { tasks ->
                _tasksState.emit(tasks)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}