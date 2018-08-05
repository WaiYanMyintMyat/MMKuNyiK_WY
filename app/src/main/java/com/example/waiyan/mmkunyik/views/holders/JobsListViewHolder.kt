package com.example.waiyan.mmkunyik.views.holders

import android.view.View
import com.example.waiyan.mmkunyik.R.drawable.job
import com.example.waiyan.mmkunyik.delegates.JobsDelegate
import com.padc.mmkunyi.data.vos.JobVO
import kotlinx.android.synthetic.main.view_item_jobs.view.*

class JobsListViewHolder : BaseViewHolder {
    private lateinit var mDelegate : JobsDelegate
    private lateinit var mJob : JobVO
    constructor(itemView: View , delegate: JobsDelegate) : super(itemView) {
        mDelegate = delegate
        itemView!!.setOnClickListener { mDelegate!!.onTapJob(mJob) }
//        itemView.btnAppply.setOnClickListener { mDelegate!!.onTapApply(mData!!) }
    }

    fun onBindData(job : JobVO) {
        mJob = job
        itemView.tvJobTitle.text = job.shortDesc
        itemView.tvJobFee.text = "$"+job.offerAmount!!.amount.toString()
        itemView.tvJobIntro.text = job.fullDesc
        itemView.tvClock.text = job.jobDuration!!.workingHoursPerDay.toString()
        itemView.tvLocation.text = job.location
    }
}