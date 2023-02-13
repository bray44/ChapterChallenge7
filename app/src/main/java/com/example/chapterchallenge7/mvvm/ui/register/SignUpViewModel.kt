package com.example.chapterchallenge7.mvvm.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.chapterchallenge7.mvvm.data.model.SignUpRequest
import com.example.chapterchallenge7.mvvm.data.model.RegisterResponse
import com.example.chapterchallenge7.mvvm.data.repository.Repository
import com.example.chapterchallenge7.mvvm.utils.Resource
import com.example.chapterchallenge7.mvvm.utils.errorResponse
import kotlinx.coroutines.Dispatchers

class SignUpViewModel(private val repository: Repository) : ViewModel() {
    fun register(
        username: String,
        email: String,
        password: String,
    ): LiveData<Resource<RegisterResponse>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                val body = SignUpRequest(
                    email = email,
                    username = username,
                    password = password
                )
                emit(Resource.success(data = repository.register(body)))
            } catch (exception: Exception) {
                emit(
                    Resource.error(
                        data = null,
                        message = exception.errorResponse()
                    )
                )
            }
        }
    }
}