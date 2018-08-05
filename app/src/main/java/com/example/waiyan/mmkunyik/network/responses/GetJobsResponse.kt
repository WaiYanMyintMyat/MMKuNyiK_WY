package com.padc.mmkunyi.network.responses

import com.google.gson.annotations.SerializedName
import com.padc.mmkunyi.data.vos.JobVO

class GetJobsResponse {


    @SerializedName("code")
    val code: Int = 0
    @SerializedName("message")
    val message: String = ""
    @SerializedName("apiVersion")
    val apiVersion: String = ""
    @SerializedName("jobs")
    val jobs: MutableList<JobVO> = ArrayList()

    fun isResponseOK(): Boolean {

        return code == 200 && jobs.count() != 0

    }
}