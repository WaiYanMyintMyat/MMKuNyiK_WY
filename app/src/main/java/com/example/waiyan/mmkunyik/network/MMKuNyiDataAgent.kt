package com.padc.mmkunyi.network

interface MMKuNyiDataAgent {

    fun loadJobList(accessToken:String,page:Int,isForceRefresh:Boolean)
}