package com.example.chapterchallenge7.mvvm.data.api

import android.content.SharedPreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val BASE_URL = "https://binar-gdd-cc8.herokuapp.com/api/"

    private fun getRetrofit(sharedPreferences: SharedPreferences): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(sharedPreferences))
            .build()
    }

    private fun okhttpClient(sharedPreferences: SharedPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(6000, TimeUnit.MILLISECONDS)
            .addInterceptor(HeaderInterceptor(sharedPreferences))
            .addInterceptor(httpLogging)
            .build()
    }

    private val httpLogging: HttpLoggingInterceptor
        get() {
            val logging = HttpLoggingInterceptor()
            return logging.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    fun apiService(sharedPreferences: SharedPreferences): ApiService {
        return getRetrofit(sharedPreferences).create(ApiService::class.java)
    }
}