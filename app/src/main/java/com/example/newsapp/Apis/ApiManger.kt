package com.example.newsapp.Apis

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level

object ApiManger {
    private var retrofit: Retrofit? = null
    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        return logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun provideOkHttpClint(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()
    }

    init {
        buildRetrofit()
    }

    private fun buildRetrofit(): Retrofit {
        if (retrofit == null) {

            retrofit =
                Retrofit.Builder().baseUrl("https://newsapi.org").client(provideOkHttpClint())
                    .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit!!


    }
    fun getInstance(): ApisServices {
        return retrofit!!.create(ApisServices::class.java)
    }
}