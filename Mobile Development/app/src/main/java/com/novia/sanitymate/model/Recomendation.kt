package com.novia.sanitymate.model

import com.google.gson.annotations.SerializedName

data class Recomendation (

    @SerializedName("emotion")
    val emotion : String,

    @SerializedName("recommendation")
    val recommendation : String,

    @SerializedName("articles")
    val articles : List<ItemBook>
)