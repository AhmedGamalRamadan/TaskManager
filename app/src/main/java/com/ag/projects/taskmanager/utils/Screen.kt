package com.ag.projects.taskmanager.utils

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data class Home(val id:Int) :Screen

    @Serializable
    data object CreateTask :Screen

    @Serializable
    data object Settings :Screen


}