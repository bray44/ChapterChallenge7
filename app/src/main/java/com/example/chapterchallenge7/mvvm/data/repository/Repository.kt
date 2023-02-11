package com.example.chapterchallenge7.mvvm.data.repository

import android.content.SharedPreferences
import com.example.chapterchallenge7.mvvm.data.api.ApiService
import com.example.chapterchallenge7.mvvm.data.model.LoginData
import com.example.chapterchallenge7.mvvm.data.model.LoginRequest
import com.example.chapterchallenge7.mvvm.data.model.LoginResponse

class Repository(private val api : ApiService, private val sharedPreferences: SharedPreferences) {

    suspend fun login(body : LoginRequest):LoginResponse{
        val response = api.login(body)
        insertToken(response.data)
        return response
    }
    private fun insertToken(data : LoginData){
        sharedPreferences.edit().apply{
            putString("token",data.token).apply()
            putString("id", data.Id).apply()
            putString("email", data.email).apply()
            putString("username", data.username).apply()
        }
    }
}