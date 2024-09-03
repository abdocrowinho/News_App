package com.example.newsapp

import com.google.gson.Gson

fun <T> String.fromJson(className:Class<T>):T{
    return Gson().fromJson(this,className)
}