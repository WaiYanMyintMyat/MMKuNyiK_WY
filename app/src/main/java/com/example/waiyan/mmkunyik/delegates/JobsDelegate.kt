package com.example.waiyan.mmkunyik.delegates

import com.padc.mmkunyi.data.vos.JobVO

/**
 *
 * Created by Phyo Thiha on 7/28/18.
 */
interface JobsDelegate {

    fun onTapJob(jobVO: JobVO)
    fun onTapApply(jobVO: JobVO)
}