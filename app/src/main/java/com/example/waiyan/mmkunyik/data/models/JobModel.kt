package com.padc.mmkunyi.data.models

import com.example.waiyan.mmkunyik.R.drawable.job
import com.example.waiyan.mmkunyik.events.ForceGetJobEvent
import com.example.waiyan.mmkunyik.events.SuccessGetJobEvent
import com.padc.mmkunyi.data.vos.JobVO
import com.padc.mmkunyi.network.MMKuNyiDataAgent
import com.padc.mmkunyi.network.RetrofitDataAgentImpl
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *
 * Created by Phyo Thiha on 7/28/18.
 */
class JobModel : BaseModel {

    private constructor() {

        mDataAgent = RetrofitDataAgentImpl.getObjectInstance()
        EventBus.getDefault().register(this)
    }


    private var mPage: Int = 1

    private var mJobRepo: HashMap<Int, JobVO> = HashMap()

    companion object {

        private var objectReference: JobModel? = null

        private var mDataAgent: MMKuNyiDataAgent? = null

        fun getObjectInstance(): JobModel? {

            if (objectReference == null) {

                objectReference = JobModel()

            }

            return objectReference
        }

    }

    fun loadJobList() {
        mDataAgent!!.loadJobList(accessToken, mPage, false)
    }

    fun loadJobListForceRefresh(){
        mPage=1
        mDataAgent!!.loadJobList(accessToken,mPage,true)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onSuccessGetJobEvent(successEvent : SuccessGetJobEvent){
        setJobDataIntoRepo(successEvent.jobList)
        mPage++
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onForceRefreshSuccessGetJob(event: ForceGetJobEvent){
        setJobDataIntoRepo(event.jobList)
        mPage++
    }

    private fun setJobDataIntoRepo(jobList: MutableList<JobVO>) {
        for (jobVo in jobList) {
            mJobRepo[jobVo.jobPostId] = jobVo
        }
    }

    fun getJobById(id : Int): JobVO? {
        return mJobRepo[id]
    }
}