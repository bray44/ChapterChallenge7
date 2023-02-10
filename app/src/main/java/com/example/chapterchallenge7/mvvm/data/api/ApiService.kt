package com.example.chapterchallenge7.mvvm.data.api

import com.example.chapterchallenge7.mvvm.data.model.LoginRequest
import com.example.chapterchallenge7.mvvm.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {


    @POST("v1/auth/login")
    suspend fun login(@Body body : LoginRequest): LoginResponse
}