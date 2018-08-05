package com.example.waiyan.mmkunyik.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.waiyan.mmkunyik.R
import com.example.waiyan.mmkunyik.delegates.JobsDelegate
import com.example.waiyan.mmkunyik.views.holders.BaseViewHolder
import com.example.waiyan.mmkunyik.views.holders.JobsListViewHolder
import com.padc.mmkunyi.data.vos.JobVO

class JobsListAdapter : BaseAdapter {

    private var mJobList : MutableList<JobVO> = ArrayList()
    private var mDelegate: JobsDelegate? = null

    constructor(delegate: JobsDelegate){
        mDelegate = delegate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsListViewHolder {
        val jobListView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_jobs,parent,false)
        return JobsListViewHolder(jobListView,mDelegate!!)
    }

    override fun getItemCount(): Int {
        return mJobList.size
    }

    override fun onBindViewHolder(holder: JobsListViewHolder, position: Int) {
        holder.onBindData(mJobList[position])
    }


    fun appendJob(jobList : List<JobVO>) {
        mJobList.addAll(jobList)
        notifyDataSetChanged()
    }

    fun setJob(jobList: MutableList<JobVO>) {
        mJobList = jobList
        notifyDataSetChanged()
    }
}