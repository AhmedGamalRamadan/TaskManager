package com.ag.projects.taskmanager.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ag.projects.taskmanager.presentation.component.TaskItem
import com.ag.projects.taskmanager.presentation.component.TaskProgressIndicator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    val viewModel: HomeScreenViewModel = getViewModel()
    val scope = rememberCoroutineScope()

    val tasks by viewModel.tasksState.collectAsStateWithLifecycle()
//    val allTasksCount = tasks.size / viewModel.getCompletedTasks(isCompleted = true)
//    val completedTasksCount = viewModel.getCompletedTasks(isCompleted = true)

    LaunchedEffect(true) {
        viewModel.getAllTasks()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            items(tasks) { task ->

                var isCompleted by remember {
                    mutableStateOf(task.isCompleted)
                }

                TaskItem(
                    navHostController = navHostController,
                    task = task,
                    isChecked = isCompleted,
                    onCheckedChanged = {
                        isCompleted = it
                        scope.launch {
                            viewModel.upsertTask(task)
                        }
                    },
                    onDelete = {
                        scope.launch {
                            viewModel.deleteTask(task.id)
                        }
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = {
                navHostController.navigate("createTask")
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add"
            )
        }
    }


}