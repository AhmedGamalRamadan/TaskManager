package com.ag.projects.taskmanager.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ag.projects.taskmanager.presentation.screen.create.CreateTaskScreen
import com.ag.projects.taskmanager.presentation.screen.details.TaskDetailsScreen
import com.ag.projects.taskmanager.presentation.screen.home.HomeScreen
import com.ag.projects.taskmanager.utils.Route
import com.ag.projects.taskmanager.utils.Screen

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.HOME) {

        composable(Route.HOME){
            HomeScreen(
                navHostController = navController
            )
        }

        composable(Route.CREATE){
            CreateTaskScreen(
                navHostController = navController
            )
        }

        composable(Route.DETAILS +"/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
            )
        ){
            val id = it.arguments?.getInt("id")
            id?.let { taskId ->
                TaskDetailsScreen(
                    navHostController = navController,
                    id = taskId
                )
            }
        }


    }
}