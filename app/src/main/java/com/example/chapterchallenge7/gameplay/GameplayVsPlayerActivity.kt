package com.example.chapterchallenge7.gameplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivityGameplayVsPlayerBinding

class GameplayVsPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameplayVsPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameplayVsPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}