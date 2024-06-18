package com.novia.sanitymate.model

import com.google.gson.annotations.SerializedName


data class PredictionEmotion(

    @SerializedName("predict")
    val predict: String,

    @SerializedName("emotion")
    val emotion: String,

    @SerializedName("datetime")
    val dateTime: String
)