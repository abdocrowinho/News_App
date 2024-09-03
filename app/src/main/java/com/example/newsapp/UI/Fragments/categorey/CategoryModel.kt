package com.example.newsapp.UI.Fragments.categorey

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel (val backGroundColor:Int , val tittle : String , val  image : Int,
val id : String
):Parcelable
