package com.example.chapterchallenge7.gameplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivityGameplayVsPlayerBinding

class GameplayVsComActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameplayVsPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Karena memiliki layout sama, activity ini membinding layout pada activity Vs COM
        binding = ActivityGameplayVsPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}