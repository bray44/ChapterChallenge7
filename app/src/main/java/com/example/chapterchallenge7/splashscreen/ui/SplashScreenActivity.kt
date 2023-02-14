package com.example.chapterchallenge7.splashscreen.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.example.chapterchallenge7.databinding.ActivitySplashScreenBinding
import com.example.chapterchallenge7.landingpage.LandingPageActivity
import com.example.chapterchallenge7.login.ui.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var mFirstInstallViewModel: FirstInstallViewModel
    private lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mFirstInstallViewModel = ViewModelProvider(this)[FirstInstallViewModel::class.java]

        val firstInstallData = mFirstInstallViewModel.createFirstInstallData("FIRST_INSTALL")

        if (mFirstInstallViewModel.getBoolean(firstInstallData, true)) {
            mFirstInstallViewModel.saveBoolean(firstInstallData, false)
            Handler().postDelayed({
                val intent = Intent(this@SplashScreenActivity, LandingPageActivity::class.java)
                startActivity(intent)
                finish()
            }, 5000)
        } else {
            Handler().postDelayed({
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 5000)
        }

    }
}