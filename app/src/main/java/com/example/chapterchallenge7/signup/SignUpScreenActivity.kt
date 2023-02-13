package com.example.chapterchallenge7.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivitySignUpScreenBinding
import com.example.chapterchallenge7.login.LoginActivity
import com.example.chapterchallenge7.mvvm.data.api.RetrofitBuilder
import com.example.chapterchallenge7.mvvm.data.repository.Repository
import com.example.chapterchallenge7.mvvm.ui.register.RegisterViewModel
import com.example.chapterchallenge7.mvvm.utils.Status
import com.google.android.material.snackbar.Snackbar

class SignUpScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpScreenBinding
    private lateinit var viewModel: RegisterViewModel
    private val sharedPreferences by lazy {
        applicationContext.getSharedPreferences("AUTHENTICATION", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
        viewModel = RegisterViewModel(
            Repository(
                api = RetrofitBuilder.apiService(sharedPreferences),
                sharedPreferences = sharedPreferences
            )
        )
        setContentView(binding.root)

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

}