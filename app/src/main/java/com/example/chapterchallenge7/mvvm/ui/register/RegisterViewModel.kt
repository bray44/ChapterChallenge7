package com.example.chapterchallenge7.mvvm.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.chapterchallenge7.mvvm.data.model.RegisterRequest
import com.example.chapterchallenge7.mvvm.data.model.RegisterResponse
import com.example.chapterchallenge7.mvvm.data.repository.Repository
import com.example.chapterchallenge7.mvvm.utils.Resource
import kotlinx.coroutines.Dispatchers

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    fun register(
        username: String,
        password: String,
    ): LiveData<Resource<RegisterResponse>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                val body = RegisterRequest(
                    username = username,
                    password = password
                )
                emit(Resource.success(data = repository.register(body)))
            } catch (exception: Exception) {
                emit(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Terjadi kesalahan"
                    )
                )
            }
        }
    }
}