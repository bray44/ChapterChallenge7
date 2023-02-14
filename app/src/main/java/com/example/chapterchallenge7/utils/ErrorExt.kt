package com.example.chapterchallenge7.utils

import com.example.chapterchallenge7.api.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException

fun Exception.errorResponse():String{
    return if (this is HttpException){
        val error : ErrorResponse = Gson().fromJson(
            this.response()?.errorBody()?.string(),
            ErrorResponse::class.java
        )
        error.error
    }else{
        this.message ?: "Terjadi Kesalahan"
    }
}