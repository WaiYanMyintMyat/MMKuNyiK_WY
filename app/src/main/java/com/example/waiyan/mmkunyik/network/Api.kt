package com.padc.mmkunyi.network

import com.example.waiyan.mmkunyik.utils.AppConstants
import com.padc.mmkunyi.network.responses.GetJobsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface Api {

    @POST(AppConstants.API_GET_JOBS)
    @FormUrlEncoded
    fun loadJobListFromServer(
            @Field(AppConstants.PARAM_TOKEN) accessToken: String
            , @Field(AppConstants.PARAM_PAGE) page: Int): Call<GetJobsResponse>
}