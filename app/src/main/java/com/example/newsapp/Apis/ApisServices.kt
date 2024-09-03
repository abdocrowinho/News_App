package com.example.newsapp.Apis

import com.example.newsapp.Apis.Models.Sources.ResourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApisServices {

    @GET("/v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKey: String? = "6fb75de9f46b4ac4b5cf7a25bce48546",
        @Query("category") category: String
    ): Call<ResourcesResponse>
    @GET ("/v2/everything")

    fun getArticles(
        @Query("apiKey") apiKey: String? = "6fb75de9f46b4ac4b5cf7a25bce48546",
        @Query("category") sources: String
    ):Call<ArticlesResponse>
}