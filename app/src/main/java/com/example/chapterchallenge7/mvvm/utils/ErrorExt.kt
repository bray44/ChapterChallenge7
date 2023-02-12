package com.example.chapterchallenge7.mvvm.utils

import com.example.chapterchallenge7.mvvm.data.model.ErrorResponse
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