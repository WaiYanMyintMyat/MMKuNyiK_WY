package com.example.waiyan.mmkunyik.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.waiyan.mmkunyik.R
import com.example.waiyan.mmkunyik.views.holders.JobsListViewHolder

class JobsListAdapter : BaseAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val jobListView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_jobs,parent,false)
        return JobsListViewHolder(jobListView)
    }

    override fun getItemCount(): Int {
        return 12
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
}