package com.novia.sanitymate.model

import com.google.gson.annotations.SerializedName


data class BaseApiResponse<D>(
    @SerializedName("Status") val status: Int,

    @SerializedName("Message") val message: String,

    @SerializedName("Data") val data:D? = null
)