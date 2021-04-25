package com.example.githubapp.model

import java.io.Serializable

data class Issue(
    val url: String ="",
    val title: String = "",
    val state: String = "",
    val labels: List<Labels>,
    val created_at: String = "",
    val user: User

) : Serializable