package com.sousa.data.network.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") val status:String?,
    @SerializedName("responceText") val responceText: String?,
    @SerializedName("userId") val userId: String?,
)