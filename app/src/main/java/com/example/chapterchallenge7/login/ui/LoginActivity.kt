package com.example.chapterchallenge7.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.chapterchallenge7.databinding.ActivityLoginBinding
import com.example.chapterchallenge7.login.data.LoginViewModel
import com.example.chapterchallenge7.mainmenu.MainMenuActivity
import com.example.chapterchallenge7.api.RetrofitBuilder
import com.example.chapterchallenge7.api.APIRepository
import com.example.chapterchallenge7.utils.Status
import com.example.chapterchallenge7.signup.ui.SignUpScreenActivity

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
            APIRepository(
                api = RetrofitBuilder.apiService(sharedPreferences),
                sharedPreferences = sharedPreferences
            )
        )
        setContentView(binding.root)
        enableButton()

        binding.etEmailLogin.doAfterTextChanged {
            enableButton()
        }
        binding.etLoginPassword.doAfterTextChanged {
            enableButton()
        }
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                email = binding.etEmailLogin.text.toString(),
                password = binding.etLoginPassword.text.toString(),
            ).observe(this){
                when(it.status){
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
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.tvDaftar.setOnClickListener {
            startActivity(Intent(this, SignUpScreenActivity::class.java))
        }
    }

    private fun enableButton() {
        val email = binding.etEmailLogin.text.toString()
        val password = binding.etLoginPassword.text.toString()
        binding.btnLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty()
    }
}