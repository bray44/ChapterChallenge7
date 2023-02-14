package com.example.chapterchallenge7.signup.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.chapterchallenge7.signup.api.SignUpRequest
import com.example.chapterchallenge7.signup.api.RegisterResponse
import com.example.chapterchallenge7.api.APIRepository
import com.example.chapterchallenge7.utils.Resource
import com.example.chapterchallenge7.utils.errorResponse
import kotlinx.coroutines.Dispatchers

class SignUpViewModel(private val APIRepository: APIRepository) : ViewModel() {
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
                emit(Resource.success(data = APIRepository.register(body)))
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