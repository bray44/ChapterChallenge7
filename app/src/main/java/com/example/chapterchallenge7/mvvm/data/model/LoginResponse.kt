package com.example.chapterchallenge7.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("succes") var succes : Boolean,
    @SerializedName("data") var data : LoginData
)

data class LoginData(
    @SerializedName("_id") var Id : String,
    @SerializedName("username") var username : String,
    @SerializedName("email") var email : String,
    @SerializedName("token") var token : String
)
