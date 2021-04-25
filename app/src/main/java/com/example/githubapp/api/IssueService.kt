package com.example.githubapp.api

import com.example.githubapp.model.Issue
import retrofit2.Call
import retrofit2.http.GET

interface IssueService {
    @GET("/repos/JetBrains/kotlin/issues")
    fun getIssue() : Call<List<Issue>>
}