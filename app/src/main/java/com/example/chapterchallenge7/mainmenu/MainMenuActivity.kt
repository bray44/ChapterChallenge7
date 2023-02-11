package com.example.chapterchallenge7.mainmenu

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivityMainMenuBinding
import com.example.chapterchallenge7.gamehistory.GameHistoryActivity
import com.example.chapterchallenge7.gameplay.GameplayViewModel
import com.example.chapterchallenge7.login.LoginActivity
import com.example.chapterchallenge7.playermode.PlayerModeActivity
import kotlin.system.exitProcess

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var sessionManager: com.example.chapterchallenge7.mainmenu.SessionManager
    private lateinit var playerDetail: HashMap<String, String?>
    private lateinit var playerName: String
    private lateinit var playerID: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        playerDetail = sessionManager.getPlayerDetail()
        playerName = playerDetail.get(sessionManager.NAME).toString()
        playerID = playerDetail.get(sessionManager.ID).toString()

        binding.tvPlayer.text = "Selamat Datang $playerName"

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
                Intent(this, GameplayViewModel ::class.java)
            )
        }

        binding.exit.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }

}