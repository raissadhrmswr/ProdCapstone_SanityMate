package com.novia.sanitymate.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize



@Parcelize
data class Team (
    val name: String,
    val university: String,
    val learningPath: String,
    val photo: Int
):Parcelable