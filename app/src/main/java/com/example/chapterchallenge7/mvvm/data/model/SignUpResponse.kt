package com.example.chapterchallenge7.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("success") var success: Boolean,
    @SerializedName("data") var data: RegisterData,
)

data class RegisterData(
    @SerializedName("_id") var id: String,
    @SerializedName("username") var username: String,
    @SerializedName("email") var email: String
)

