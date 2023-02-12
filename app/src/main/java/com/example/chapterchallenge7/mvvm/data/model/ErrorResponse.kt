package com.example.chapterchallenge7.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status") var status : String,
    @SerializedName("error") var error : String
)
