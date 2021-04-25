package com.example.githubapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.model.Issue

class AdapterIssue(
    private val issueList: List<Issue> = listOf(),
    private val clickListener: OnClickListener
) : RecyclerView.Adapter<AdapterIssue.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.issue_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val issue = issueList[position]

        holder.txt_title.text = issue.title
        holder.txt_state.text = if(issue.state == "open"){
            holder.txt_state.setTextColor(Color.parseColor("#1F7629"))
            "ABERTO"
        }else {
            holder.txt_state.setTextColor(Color.parseColor("#E22C28"))
            "FECHADO"
        }

        holder.itemView.setOnClickListener { clickListener.onClick(issue) }
    }

    override fun getItemCount() = issueList.size

    interface  OnClickListener {
        fun onClick(issue: Issue)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txt_title: TextView = itemView.findViewById(R.id.txt_title)
        val txt_state: TextView = itemView.findViewById(R.id.txt_state)
    }

}