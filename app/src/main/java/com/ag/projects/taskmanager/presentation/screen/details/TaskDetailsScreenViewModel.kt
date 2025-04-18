package com.ag.projects.taskmanager.presentation.screen.details

import androidx.lifecycle.ViewModel
import com.ag.projects.taskmanager.domain.usecase.details.GetTaskDetailsUseCase

class TaskDetailsScreenViewModel(
    private val getTaskDetails: GetTaskDetailsUseCase
) :ViewModel(){

}