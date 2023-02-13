package com.example.chapterchallenge7.mvvm.data.repository

import android.content.SharedPreferences
import com.example.chapterchallenge7.mvvm.data.api.ApiService
import com.example.chapterchallenge7.mvvm.data.model.*

class Repository(private val api: ApiService, private val sharedPreferences: SharedPreferences) {

    suspend fun register(body: RegisterRequest): RegisterResponse {
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