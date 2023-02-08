package com.example.chapterchallenge7.landingpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivityLandingPageBinding

class LandingPageActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLandingPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
    }
}