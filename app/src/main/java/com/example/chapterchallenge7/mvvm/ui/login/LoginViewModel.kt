package com.example.chapterchallenge7.mvvm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.chapterchallenge7.mvvm.data.model.LoginRequest
import com.example.chapterchallenge7.mvvm.data.model.LoginResponse
import com.example.chapterchallenge7.mvvm.data.repository.Repository
import com.example.chapterchallenge7.mvvm.utils.Resource
import com.example.chapterchallenge7.mvvm.utils.errorResponse
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

class LoginViewModel(private val repository: Repository):ViewModel() {
    fun login(username : String, password : String): LiveData<Resource<LoginResponse>>{
        return liveData(Dispatchers.IO){
            emit(Resource.loading(data = null))
            try {
                val body = LoginRequest(
                    username = username,
                    password = password
                )
                emit(Resource.success(repository.login(body)))
            }catch (e : Exception){
                emit(Resource.error(data = null, message = e.errorResponse()))
            }
        }
    }
}