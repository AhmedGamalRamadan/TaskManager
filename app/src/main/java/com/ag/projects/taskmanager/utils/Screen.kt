package com.ag.projects.taskmanager.utils

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data object Home : Screen

    @Serializable
    data object CreateTask : Screen

    @Serializable
    data object Settings : Screen


}

object Route {

    const val HOME = "home"
    const val CREATE = "create"
    const val DETAILS = "details"
}