package com.ag.projects.taskmanager.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.taskmanager.data.local.Priority
import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.domain.usecase.delete.DeleteUseCase
import com.ag.projects.taskmanager.domain.usecase.get_all.GetAllTasksUseCase
import com.ag.projects.taskmanager.domain.usecase.get_by_priority.GetTasksSortedByPriority
import com.ag.projects.taskmanager.domain.usecase.get_by_sorted_date.GetTasksSortedByDateUseCase
import com.ag.projects.taskmanager.domain.usecase.get_by_sorted_title.GetTasksSortedByTitle
import com.ag.projects.taskmanager.domain.usecase.get_complete_tasks.GetCompletedTasksUseCase
import com.ag.projects.taskmanager.domain.usecase.upsert.UpsertUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val getCompletedTasksUseCase: GetCompletedTasksUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val upsertUseCase: UpsertUseCase,
    private val sortedByTitle: GetTasksSortedByTitle,
    private val sortedByDateUseCase: GetTasksSortedByDateUseCase,
    private val sortedByPriority: GetTasksSortedByPriority
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.onStart {
        getAllTasks()
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

     fun getAllTasks() {
        viewModelScope.launch {
            try {
                getAllTasksUseCase().collect {
                    _tasks.emit(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getCompletedTasks(isCompleted: Boolean) {
        viewModelScope.launch {
            try {
                _tasks.emit(emptyList())
                getCompletedTasksUseCase(isCompleted = isCompleted).collect {
                    _tasks.emit(it)
                }
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

    fun upsertTask(task: Task) {
        viewModelScope.launch {
            try {
                upsertUseCase(task)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTasksSortedByTitle(){
        viewModelScope.launch {
            try {
                sortedByTitle().collect{
                    _tasks.emit(it)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun getTasksSortedByPriority(priority: Priority){
        viewModelScope.launch {
            try {
                sortedByPriority(priority).collect{
                    _tasks.emit(it)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun getTasksSortedByDate(){
        viewModelScope.launch {
            try {
                sortedByDateUseCase().collect{
                    _tasks.emit(it)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}