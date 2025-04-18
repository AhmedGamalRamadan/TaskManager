package com.ag.projects.taskmanager.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ag.projects.taskmanager.presentation.screen.create.CreateTaskScreen
import com.ag.projects.taskmanager.presentation.screen.details.TaskDetailsScreen
import com.ag.projects.taskmanager.presentation.screen.home.HomeScreen
import com.ag.projects.taskmanager.utils.Screen

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home) {

        composable<Screen.Home> {
            HomeScreen(
                navHostController = navController
            )
        }

        composable<Screen.CreateTask> {
            CreateTaskScreen(
                navHostController = navController
            )
        }

        composable<Screen.TaskDetails> {
            val args = it.toRoute<Screen.TaskDetails>()
            TaskDetailsScreen(
                navHostController = navController,
                id = args.id
            )
        }
    }
}