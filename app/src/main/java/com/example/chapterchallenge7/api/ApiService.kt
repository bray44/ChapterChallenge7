package com.example.chapterchallenge7.api

import com.example.chapterchallenge7.login.api.LoginRequest
import com.example.chapterchallenge7.login.api.LoginResponse
import com.example.chapterchallenge7.signup.api.SignUpRequest
import com.example.chapterchallenge7.signup.api.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("v1/auth/register")
    suspend fun register(@Body body: SignUpRequest): RegisterResponse

    @POST("v1/auth/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse
}