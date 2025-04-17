package com.ag.projects.taskmanager.presentation.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ag.projects.taskmanager.data.local.Priority
import com.ag.projects.taskmanager.data.local.TaskFilter
import com.ag.projects.taskmanager.data.local.TaskSort
import com.ag.projects.taskmanager.presentation.component.SortedBottomSheet
import com.ag.projects.taskmanager.presentation.component.TaskItem
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    val viewModel: HomeScreenViewModel = koinViewModel()
    val scope = rememberCoroutineScope()
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()

    val bottomSheetState = rememberModalBottomSheetState()

    val allFilters = remember {
        mutableListOf(
            "All",
            "Completed",
            "Pending",
            "Sort By Title",
            "Sort By Date",
            "Sort By Low ",
            "Sort By Medium ",
            "Sort By High "
        )
    }

    val filters = remember {
        mutableListOf(
            TaskFilter.ALL,
            TaskFilter.COMPLETED,
            TaskFilter.PENDING,
        )
    }

    val sortedOptions = remember {
        mutableListOf(
            TaskSort.TITLE,
            TaskSort.PRIORITY,
            TaskSort.CREATION_DATE,
        )
    }
    var selectedSortOption by remember { mutableStateOf(TaskSort.TITLE) }


    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    var checkboxSorted by remember {
        mutableStateOf(false)
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

            // for filter by completed , pending or All
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
                                when (index) {
                                    0-> viewModel.getAllTasks()
                                    1-> viewModel.getCompletedTasks(isCompleted = true)
                                    2-> viewModel.getCompletedTasks(isCompleted = false)
                                    3-> viewModel.getTasksSortedByTitle()
                                    4-> viewModel.getTasksSortedByDate()
                                    5-> viewModel.getTasksSortedByPriority(priority = Priority.LOW)
                                    6-> viewModel.getTasksSortedByPriority(priority = Priority.MEDIUM)
                                    7-> viewModel.getTasksSortedByPriority(priority = Priority.HIGH)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected) Color.Blue else Color.White,
                                contentColor = if (isSelected) Color.White else Color.Black,
                            )
                        ) {
                            Text(text = filter.toString())
                        }
                    }

                    item {
                        Icon(
                            imageVector = Icons.Filled.Sort,
                            contentDescription = "Sort",
                            modifier = modifier
                                .size(40.dp)
                                .clickable {
                                    scope.launch {
                                        bottomSheetState.show()
                                    }
                                }
                        )
                    }
                }
            }

            // display al tasks
            items(tasks, key = { it.id }) { task ->

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

        if (bottomSheetState.isVisible) {
            SortedBottomSheet(
                modifier = modifier,
                sheetState = bottomSheetState,
                sortedOptions = sortedOptions,
                checked = checkboxSorted,
                taskSort = selectedSortOption,
                onCheckedChanged = {
                    checkboxSorted = it
                },
                onOptionsSelected = { selected ->
                    selectedSortOption = selected

                    when(selected){
                        TaskSort.TITLE -> viewModel.getTasksSortedByTitle()
                        TaskSort.PRIORITY -> viewModel.getTasksSortedByPriority(Priority.HIGH)
                        TaskSort.CREATION_DATE -> viewModel.getTasksSortedByDate()
                    }
                    checkboxSorted = false

                }
            )
        }
    }
}