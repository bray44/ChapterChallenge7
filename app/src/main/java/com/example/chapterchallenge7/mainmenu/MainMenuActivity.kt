package com.example.chapterchallenge7.mainmenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapterchallenge7.databinding.ActivityMainMenuBinding
import com.example.chapterchallenge7.gamehistory.ui.GameHistoryActivity
import com.example.chapterchallenge7.playermode.PlayerModeActivity
import com.example.chapterchallenge7.tutorial.TutorialActivity
import kotlin.system.exitProcess

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var playerDetail: HashMap<String, String?>
    private lateinit var playerName: String
    private lateinit var playerID: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)







        binding.play.setOnClickListener {
            startActivity(
                Intent(this, PlayerModeActivity::class.java)
            )
        }

        binding.history.setOnClickListener {
            startActivity(
                Intent(this, GameHistoryActivity::class.java)
            )
        }

        binding.tutorial.setOnClickListener {
            startActivity(
                Intent(this, TutorialActivity ::class.java)
            )
        }

        binding.exit.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }

}