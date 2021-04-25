package com.example.githubapp.activitys

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.R
import com.example.githubapp.adapter.AdapterIssue
import com.example.githubapp.api.IssueService
import com.example.githubapp.model.Issue
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), AdapterIssue.OnClickListener {

    private lateinit var adapterIssue: AdapterIssue
    private var issueList: MutableList<Issue> = mutableListOf()

    private val URL_BASE = "https://api.github.com"
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicia Retrofit
        startRetrofit()

        // Realiza a chamada via Retrofit
        getIssue()

    }

    // Inicializa o RecyclerView
    private fun configRv(){
        text_info.text = ""
        progressBar.visibility = View.GONE

        rv_issues.layoutManager = LinearLayoutManager(this)
        rv_issues.setHasFixedSize(true)
        adapterIssue = AdapterIssue(issueList, this)
        rv_issues.adapter = adapterIssue
    }

    // Inicia Retrofit
    private fun startRetrofit() {
        retrofit = Retrofit
            .Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Realiza a chamada via Retrofit
    private fun getIssue() {
        val issueService = retrofit.create(IssueService::class.java)
        val call = issueService.getIssue()

        call.enqueue(object : Callback<List<Issue>> {
            override fun onResponse(call: Call<List<Issue>>, response: Response<List<Issue>>) {
                if (response.isSuccessful) {
                    issueList = (response.body() as MutableList<Issue>?)!!
                }
                configRv()
            }

            override fun onFailure(call: Call<List<Issue>>, t: Throwable) {
            }

        })
    }

    // Abre activity de detalhes do Issue
    override fun onClick(issue: Issue) {
        val intent = Intent(this, DetailIssueActivity::class.java)
        intent.putExtra("issue", issue)
        startActivity(intent)
    }

}