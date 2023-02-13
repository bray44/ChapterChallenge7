package com.example.chapterchallenge7.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModel
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivityLoginBinding
import com.example.chapterchallenge7.databinding.ActivitySignUpScreenBinding
import com.example.chapterchallenge7.mainmenu.MainMenuActivity
import com.example.chapterchallenge7.mvvm.data.api.RetrofitBuilder
import com.example.chapterchallenge7.mvvm.data.repository.Repository
import com.example.chapterchallenge7.mvvm.ui.login.LoginViewModel
import com.example.chapterchallenge7.mvvm.utils.Status
import com.example.chapterchallenge7.signup.SignUpScreenActivity
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private val sharedPreferences by lazy {
        applicationContext.getSharedPreferences("AUTHENTICATION", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = LoginViewModel(
            Repository(
                api = RetrofitBuilder.apiService(sharedPreferences),
                sharedPreferences = sharedPreferences
            )
        )
        setContentView(binding.root)
        enableButton()

        binding.etUsername.doAfterTextChanged {
            enableButton()
        }
        binding.etLoginPassword.doAfterTextChanged {
            enableButton()
        }
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                username = binding.etUsername.toString(),
                password = binding.etLoginPassword.toString(),
            ).observe(this) {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.btnLogin.visibility = View.VISIBLE
                        binding.pbLoading.visibility = View.GONE
                        startActivity(Intent(this, MainMenuActivity::class.java))
                    }
                    Status.LOADING -> {
                        binding.btnLogin.visibility = View.GONE
                        binding.pbLoading.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        binding.btnLogin.visibility = View.VISIBLE
                        binding.pbLoading.visibility = View.GONE
                    }
                }
            }
        }
        binding.tvDaftar.setOnClickListener {
            startActivity(Intent(this, SignUpScreenActivity::class.java))
        }
    }

    private fun enableButton() {
        val email = binding.etUsername.text.toString()
        val password = binding.etLoginPassword.text.toString()
        binding.btnLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty()
    }
}