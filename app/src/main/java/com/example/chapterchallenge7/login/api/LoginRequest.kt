package com.example.chapterchallenge7.login.api

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") var email : String,
    @SerializedName("password") var password : String
)
