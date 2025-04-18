package com.ag.projects.taskmanager.presentation.screen.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ag.projects.taskmanager.data.local.Priority
import com.ag.projects.taskmanager.presentation.component.CustomTextField
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun TaskDetailsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    id: Int
) {

    val viewModel: TaskDetailsScreenViewModel = koinViewModel()
    val taskState by viewModel.task.collectAsStateWithLifecycle()

    val sdf = SimpleDateFormat("dd MMMM, yyyy -HH:mm")
    val currentDateAndTime: String = sdf.format(Date())

    val priorities = remember {
        mutableStateListOf(Priority.LOW, Priority.MEDIUM, Priority.HIGH)
    }

    LaunchedEffect(true) {
        id?.let {
            viewModel.getTaskDetails(it)
            Log.d("TaskDetailsScreen", "task id is $it")
        }
    }

    taskState?.let { task ->

        var title by rememberSaveable {
            mutableStateOf(task.title)
        }

        var description by rememberSaveable {
            mutableStateOf(task.description)
        }
        var selectedOption by rememberSaveable { mutableStateOf(task.priority) }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomTextField(
                modifier = modifier,
                value = title,
                onValueChange = { title = it },
                label = "Title"
            )

            Spacer(Modifier.height(8.dp))

            CustomTextField(
                modifier = modifier,
                value = description,
                onValueChange = { description = it },
                label = "Description"
            )

            priorities.forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedOption = option }
                        .padding(8.dp)
                ) {
                    Checkbox(
                        checked = selectedOption == option,
                        onCheckedChange = {
                            selectedOption = option
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Green,
                        )
                    )
                    Text(
                        text = option.toString(),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Button(
                onClick = {
                    val updatedTask = task.copy(
                        title = title,
                        description = description,
                        priority = selectedOption,
                        createdAt = currentDateAndTime
                    )
                    viewModel.upsertTask(updatedTask)

                    navHostController.navigateUp()
                },
                enabled = task.title.trim().isNotEmpty(),

                ) {
                Text(
                    text = "Update Task"
                )
            }
        }
    }

}