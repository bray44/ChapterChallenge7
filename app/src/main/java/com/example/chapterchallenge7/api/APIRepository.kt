package com.example.chapterchallenge7.api

import android.content.SharedPreferences
import com.example.chapterchallenge7.login.api.LoginData
import com.example.chapterchallenge7.login.api.LoginRequest
import com.example.chapterchallenge7.login.api.LoginResponse
import com.example.chapterchallenge7.signup.api.RegisterResponse
import com.example.chapterchallenge7.signup.api.SignUpRequest

class APIRepository(private val api: ApiService, private val sharedPreferences: SharedPreferences) {

    suspend fun register(body: SignUpRequest): RegisterResponse {
        return api.register(body)
    }

    suspend fun login(body: LoginRequest): LoginResponse {
        val response = api.login(body)
        insertToken(response.data)
        return response
    }

    private fun insertToken(data: LoginData) {
        sharedPreferences.edit().apply {
            putString("token", data.token).apply()
            putString("id", data.id).apply()
            putString("email", data.email).apply()
            putString("username", data.username).apply()
        }
    }
}