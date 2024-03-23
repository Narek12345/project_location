package com.sousa.data.network.models

import com.google.gson.annotations.SerializedName

class SimpleResponse(
    @SerializedName("status") val status:String?,
    @SerializedName("responceText") val responceText: String?
)