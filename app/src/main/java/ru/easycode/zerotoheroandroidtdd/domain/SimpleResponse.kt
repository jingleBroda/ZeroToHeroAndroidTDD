package ru.easycode.zerotoheroandroidtdd.domain

import com.google.gson.annotations.SerializedName

data class SimpleResponse(
    @SerializedName("text")
    val text: String
)