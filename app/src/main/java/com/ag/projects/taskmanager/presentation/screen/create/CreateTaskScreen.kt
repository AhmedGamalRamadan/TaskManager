package com.ag.projects.taskmanager.presentation.screen.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ag.projects.taskmanager.data.local.Priority
import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.utils.Screen
import org.koin.androidx.compose.getViewModel
import java.util.Date

@Composable
fun CreateTaskScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    val viewModel: CreateTaskScreenViewModel = getViewModel()

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf<String>("")
    }

    val priorities = remember {
        mutableListOf(Priority.LOW, Priority.MEDIUM, Priority.HIGH)
    }

    var selectedOption by remember { mutableStateOf(Priority.MEDIUM) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            label = {
                Text("Title")
            },
            shape = RoundedCornerShape(15.dp)
        )

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            label = {
                Text("Description")
            },
            shape = RoundedCornerShape(15.dp)
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
                viewModel.upsertTask(
                    Task(
                        title = title,
                        description = description,
                        priority = selectedOption,
                        isCompleted = false,
                        createdAt = Date()
                    )
                )
                navHostController.navigateUp()
            }
        ) {
            Text(
                text = "Save"
            )
        }


    }
}