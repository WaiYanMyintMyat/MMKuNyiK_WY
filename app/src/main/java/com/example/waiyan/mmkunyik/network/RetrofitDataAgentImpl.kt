package com.padc.mmkunyi.network

import android.util.Log
import com.example.waiyan.mmkunyik.events.ApiErrorEvent
import com.example.waiyan.mmkunyik.events.ForceGetJobEvent
import com.example.waiyan.mmkunyik.events.SuccessGetJobEvent
import com.example.waiyan.mmkunyik.utils.AppConstants
import com.google.gson.Gson
import com.padc.mmkunyi.network.responses.GetJobsResponse
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitDataAgentImpl : MMKuNyiDataAgent {

    private var mApi: Api? = null

    companion object {

        private var objectReference: RetrofitDataAgentImpl? = null

        fun getObjectInstance(): RetrofitDataAgentImpl? {

            if (objectReference == null) {

                objectReference = RetrofitDataAgentImpl()
            }
            return objectReference
        }
    }


    private constructor() {

        val mOkHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(AppConstants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()

        mApi = retrofit.create(Api::class.java)


    }

    override fun loadJobList(accessToken: String, page: Int, isForceRefresh: Boolean) {

        val apiCall: Call<GetJobsResponse> = mApi!!.loadJobListFromServer(accessToken, page)
        apiCall.enqueue(object : Callback<GetJobsResponse> {

            override fun onResponse(call: Call<GetJobsResponse>?, response: Response<GetJobsResponse>?) {
                val getJobsResponse: GetJobsResponse? = response!!.body()
                if(getJobsResponse!!.isResponseOK() && getJobsResponse != null){

                    if(isForceRefresh){
                        val forceSuccessEvent = ForceGetJobEvent(getJobsResponse.jobs)
                        EventBus.getDefault().post(forceSuccessEvent)
                    }else{
                        val successEvent = SuccessGetJobEvent(getJobsResponse.jobs)
                        EventBus.getDefault().post(successEvent)
                    }
                }else{
                    if(getJobsResponse == null){
                        val errorEvent = ApiErrorEvent(AppConstants.ERROR_RESPONSE_EMPTY)
                        EventBus.getDefault().post(errorEvent)
                    }else{
                        val errorEvent = ApiErrorEvent(getJobsResponse.message)
                        EventBus.getDefault().post(errorEvent)
                    }
                }
            }

            override fun onFailure(call: Call<GetJobsResponse>?, t: Throwable?) {
                val errorEvent = ApiErrorEvent(t!!.localizedMessage)
                EventBus.getDefault().post(errorEvent)
            }

        })
    }
}