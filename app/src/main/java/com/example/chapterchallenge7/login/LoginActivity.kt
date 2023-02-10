package com.example.chapterchallenge7.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivityLoginBinding
import com.example.chapterchallenge7.databinding.ActivitySignUpScreenBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableButton()

        binding.etLoginEmail.doAfterTextChanged {
            enableButton()
        }
        binding.etLoginPassword.doAfterTextChanged {
            enableButton()
        }
    }

    private fun enableButton() {
        val email = binding.etLoginEmail.text.toString()
        val password = binding.etLoginPassword.text.toString()

        binding.btnLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty()
    }
}