package com.example.chapterchallenge7.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.landingpage.LandingPageActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity,LandingPageActivity::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}