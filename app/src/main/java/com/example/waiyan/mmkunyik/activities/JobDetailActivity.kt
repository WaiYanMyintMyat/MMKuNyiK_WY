package com.example.waiyan.mmkunyik.activities

import android.os.Bundle
import com.example.waiyan.mmkunyik.R
import com.example.waiyan.mmkunyik.utils.AppConstants
import com.padc.mmkunyi.data.models.JobModel
import com.padc.mmkunyi.data.vos.JobVO
import kotlinx.android.synthetic.main.activity_job_detail.*

class JobDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_detail)

        val jobID = intent.extras.getInt(AppConstants.JOB_ID)
        val jobVo: JobVO = JobModel.getObjectInstance()!!.getJobById(jobID)!!

        tvJobTitle.text = jobVo.shortDesc
        tvDescription.text = jobVo.fullDesc
        tvPrice.text = "$"+jobVo.offerAmount!!.amount.toString()
        tvLocation.text = jobVo.location
    }
}