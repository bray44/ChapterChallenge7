package com.example.chapterchallenge7.splashscreen

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivitySplashScreenBinding
import com.example.chapterchallenge7.landingpage.LandingPageActivity
import com.example.chapterchallenge7.mvvm.data.api.ApiService
import com.example.chapterchallenge7.mvvm.data.model.LoginData
import com.example.chapterchallenge7.mvvm.data.repository.Repository

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity,LandingPageActivity::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}