package com.example.chapterchallenge7.signup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.chapterchallenge7.databinding.ActivitySignUpScreenBinding
import com.example.chapterchallenge7.login.ui.LoginActivity
import com.example.chapterchallenge7.api.RetrofitBuilder
import com.example.chapterchallenge7.api.APIRepository
import com.example.chapterchallenge7.utils.Status
import com.example.chapterchallenge7.signup.data.SignUpViewModel
import com.google.android.material.snackbar.Snackbar

class SignUpScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpScreenBinding
    private lateinit var viewModel: SignUpViewModel
    private val sharedPreferences by lazy {
        applicationContext.getSharedPreferences("AUTHENTICATION", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
        viewModel = SignUpViewModel(
            APIRepository(
                api = RetrofitBuilder.apiService(sharedPreferences),
                sharedPreferences = sharedPreferences
            )
        )
        setContentView(binding.root)
        enableButton()

        binding.etSignupName.doAfterTextChanged {
            enableButton()
        }
        binding.etSignupPassword.doAfterTextChanged {
            enableButton()
        }
        binding.btnSignup.setOnClickListener {
            viewModel.register(
                email = binding.etEmail.text.toString(),
                password = binding.etSignupPassword.text.toString(),
                username = binding.etSignupName.text.toString(),
            ).observe(this) {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.btnSignup.visibility = View.VISIBLE
                        binding.pbLoading.visibility = View.GONE
                        Snackbar.make(
                            binding.root, it.data?.data?.id.orEmpty(), Snackbar.LENGTH_INDEFINITE
                        ).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                    Status.LOADING -> {
                        binding.btnSignup.visibility = View.GONE
                        binding.pbLoading.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        binding.btnSignup.visibility = View.VISIBLE
                        binding.pbLoading.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.tvSignUpTitle.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun enableButton() {
        val username = binding.etSignupName.text.toString()
        val password = binding.etSignupPassword.text.toString()
        binding.btnSignup.isEnabled = username.isNotEmpty() && password.isNotEmpty()
    }
}