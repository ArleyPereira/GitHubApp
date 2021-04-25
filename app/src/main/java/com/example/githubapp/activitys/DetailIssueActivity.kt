package com.example.githubapp.activitys

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.githubapp.R
import com.example.githubapp.model.Issue
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_issue.*

class DetailIssueActivity : AppCompatActivity() {

    private lateinit var issue: Issue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_issue)

        // Habilita o botão de voltar e seta um icone
        configToolbar()

        // Exibe as informações os componentes em tela
        configDetail()

    }

    // Abre o Issue no navegador
    private fun openIssue() {
        btn_open_issue.setOnClickListener {
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = (Uri.parse(issue.url))
            startActivity(intent)
        }
    }

    // Habilita o botão de voltar e seta um icone
    private fun configToolbar(){
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    // Exibe as informações os componentes em tela
    private fun configDetail(){
        val bundle = intent.extras
        if(bundle != null) run {
            issue = bundle.getSerializable("issue") as Issue
            txt_title.text = issue.title
            if(issue.labels.isNotEmpty()){
                txt_description.text = if(issue.labels[0].description.isNotEmpty()){
                    issue.labels[0].description
                }else {
                    "Sem descrição."
                }
            }

            txt_created.text = issue.created_at
            Picasso.get().load(issue.user.avatar_url).into(img_avatar)

            // Abre o Issue no navegador
            openIssue()
        }
    }

    // Fecha a activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return true
    }

}