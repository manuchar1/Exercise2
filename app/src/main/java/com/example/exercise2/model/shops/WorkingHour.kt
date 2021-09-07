package com.example.exercise2.model.shops


import com.google.gson.annotations.SerializedName

data class WorkingHour(
    @SerializedName("day")
    val day: String,
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String,
    @SerializedName("working")
    val working: Boolean
)