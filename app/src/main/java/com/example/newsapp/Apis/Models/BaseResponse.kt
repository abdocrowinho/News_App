package com.example.newsapp.Apis.Models

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @field:SerializedName("status")
    val status: String? = null
    @field:SerializedName("code")
    val code: String? = null
    @field:SerializedName("message")
    val message: String? = null
}