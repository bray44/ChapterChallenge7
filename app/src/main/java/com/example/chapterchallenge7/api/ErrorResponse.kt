package com.example.chapterchallenge7.api

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status") var status : String,
    @SerializedName("error") var error : String
)
