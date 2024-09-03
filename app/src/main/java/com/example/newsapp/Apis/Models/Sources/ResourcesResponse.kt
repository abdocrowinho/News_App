package com.example.newsapp.Apis.Models.Sources

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResourcesResponse(

    @field:SerializedName("sources")
    val sources: List<SourcesItem?>? = null,

    ) : Parcelable