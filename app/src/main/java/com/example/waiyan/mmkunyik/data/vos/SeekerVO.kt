package com.padc.mmkunyi.data.vos

import com.google.gson.annotations.SerializedName


class SeekerVO {

    @SerializedName("seekerId")
    val seekerId: Int = 0
    @SerializedName("seekerName")
    val seekerName: String = ""
    @SerializedName("seekerProfilePicUrl")
    val seekerProfilePicUrl: String = ""
    @SerializedName("seekerSkill")
    val seekerSkill: List<SeekerSkillVO> = ArrayList()
    @SerializedName("viewedTimeStamp")
    val viewedTimeStamp: String = ""

}