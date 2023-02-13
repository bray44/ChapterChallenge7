package com.example.chapterchallenge7.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("success") var success : Boolean,
    @SerializedName("data") var data : LoginData
)

data class LoginData(
    @SerializedName("_id") var id : String,
    @SerializedName("username") var username : String,
    @SerializedName("email") var email: String,
    @SerializedName("token") var token : String
)
