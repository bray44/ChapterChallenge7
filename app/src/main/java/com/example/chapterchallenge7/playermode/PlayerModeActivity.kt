package com.example.chapterchallenge7.playermode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivityPlayerModeBinding
import com.example.chapterchallenge7.gameplay.GameplayVsComActivity
import com.example.chapterchallenge7.gameplay.GameplayVsPlayerActivity
import com.example.chapterchallenge7.mainmenu.MainMenuActivity

class PlayerModeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerModeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerModeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        binding.ivClose.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))
        }

        binding.player.setOnClickListener {
            startActivity(Intent(this, GameplayVsPlayerActivity::class.java))
        }

        binding.computer.setOnClickListener {
            startActivity(Intent(this, GameplayVsComActivity::class.java))
        }
    }
}