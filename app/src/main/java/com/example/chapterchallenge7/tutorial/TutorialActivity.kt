package com.example.chapterchallenge7.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chapterchallenge7.databinding.ActivityTutorialBinding
import com.example.chapterchallenge7.mainmenu.MainMenuActivity

class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityTutorialBtnExit.setOnClickListener{
            startActivity(Intent(this, MainMenuActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release()
    }
}