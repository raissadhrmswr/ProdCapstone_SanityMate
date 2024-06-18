package com.novia.sanitymate.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ItemBook (
    @SerializedName("title")
    val title: String,

    @SerializedName("link")
    val link: String
) : Parcelable {
    override fun toString(): String {
        return "ItemBook(title='$title', link='$link')"
    }
}