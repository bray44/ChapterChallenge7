package com.example.chapterchallenge7.login.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.chapterchallenge7.login.api.LoginRequest
import com.example.chapterchallenge7.login.api.LoginResponse
import com.example.chapterchallenge7.api.APIRepository
import com.example.chapterchallenge7.utils.Resource
import com.example.chapterchallenge7.utils.errorResponse
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val APIRepository: APIRepository):ViewModel() {
    fun login(email : String, password : String): LiveData<Resource<LoginResponse>>{
        return liveData(Dispatchers.IO){
            emit(Resource.loading(data = null))
            try {
                val body = LoginRequest(
                    email = email,
                    password = password
                )
                emit(Resource.success(APIRepository.login(body)))
            }catch (e : Exception){
                emit(Resource.error(data = null, message = e.errorResponse()))
            }
        }
    }
}