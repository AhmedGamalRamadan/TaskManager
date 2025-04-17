package com.ag.projects.taskmanager.presentation.screen.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.getViewModel

@Composable
fun CreateTaskScreen(modifier: Modifier = Modifier) {

    val viewModel:CreateTaskScreenViewModel = getViewModel()

    Column(
        modifier= Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {


    }
}