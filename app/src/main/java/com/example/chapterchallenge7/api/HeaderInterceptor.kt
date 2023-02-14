package com.example.chapterchallenge7.api

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val sharedPreferences: SharedPreferences): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = sharedPreferences.getString("token","")
        if (!token.isNullOrEmpty()){
            request.header("Authorization","Bearer $token")
        }
        return chain.proceed(request.build())
    }
}