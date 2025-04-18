package com.ag.projects.taskmanager.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ag.projects.taskmanager.R
import com.ag.projects.taskmanager.data.local.Priority
import com.ag.projects.taskmanager.presentation.component.ShimmerListItem
import com.ag.projects.taskmanager.presentation.component.TaskItem
import com.ag.projects.taskmanager.utils.TaskActionFilter
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    val viewModel: HomeScreenViewModel = koinViewModel()
    val scope = rememberCoroutineScope()
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()

    val allFilters = remember {
        mutableListOf(
            TaskActionFilter.All,
            TaskActionFilter.Completed,
            TaskActionFilter.Pending,
            TaskActionFilter.SortByTitle,
            TaskActionFilter.SortByDate,
            TaskActionFilter.SortByPriority(Priority.LOW),
            TaskActionFilter.SortByPriority(Priority.MEDIUM),
            TaskActionFilter.SortByPriority(Priority.HIGH),
        )
    }

    var selectedIndex by remember {
        mutableIntStateOf(0)
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

            // for filter by completed , pending or All and sorting as a tabs
            item {
                LazyRow(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    itemsIndexed(allFilters) { index, filter ->
                        val isSelected = selectedIndex == index
                        Button(
                            onClick = {
                                selectedIndex = index
                                when (filter) {
                                    is TaskActionFilter.All -> viewModel.getAllTasks()
                                    is TaskActionFilter.Completed -> viewModel.getCompletedTasks(
                                        true
                                    )

                                    is TaskActionFilter.Pending -> viewModel.getCompletedTasks(false)
                                    is TaskActionFilter.SortByTitle -> viewModel.getTasksSortedByTitle()
                                    is TaskActionFilter.SortByDate -> viewModel.getTasksSortedByDate()
                                    is TaskActionFilter.SortByPriority -> viewModel.getTasksSortedByPriority(
                                        filter.priority
                                    )
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected) Color.Blue else Color.White,
                                contentColor = if (isSelected) Color.White else Color.Black,
                            )
                        ) {
                            Text(text = filter.label)
                        }
                    }
                }
            }

            // display all tasks
            if (tasks.tasks.isNotEmpty()) {

                items(tasks.tasks, key = { it.id }) { task ->

                    var isCompleted by remember {
                        mutableStateOf(task.isCompleted)
                    }

                    TaskItem(
                        navHostController = navHostController,
                        task = task,
                        isCompleted = isCompleted,
                        onCheckedChanged = { checked ->
                            isCompleted = checked
                            val updatedTask = task.copy(isCompleted = checked)

                            scope.launch {
                                viewModel.upsertTask(updatedTask)
                            }
                        },
                        onDelete = { viewModel.deleteTask(task.id) }
                    )
                }
            }

            if (tasks.tasks.isEmpty()){
               item {
                   Box(
                       modifier = Modifier.fillMaxSize().padding(12.dp),
                       contentAlignment = Alignment.Center
                   ) {
                       Icon(
                           painter = painterResource(R.drawable.empty_tasks),
                           contentDescription = "noTasksFounded",
                           modifier = modifier
                               .fillMaxWidth()
                               .height(250.dp),
                           tint = Color.Blue
                       )
                   }
               }
            }
            if (tasks.isLoading) {
                items(8) {
                    ShimmerListItem(
                        isLoading = true,
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = {
                navHostController.navigate("createTask")
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 14.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add"
            )
        }

    }
}