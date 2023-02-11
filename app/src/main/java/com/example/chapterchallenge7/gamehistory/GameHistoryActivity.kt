package com.example.chapterchallenge7.gamehistory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Delete
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivityGameHistoryBinding
import com.example.chapterchallenge7.gamehistory.data.GameHistoryAdapter
import com.example.chapterchallenge7.mainmenu.MainMenuActivity

class GameHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameHistoryBinding
    private lateinit var mGameHistoryViewModel: GameHistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = GameHistoryAdapter()
        binding.rvGameHistory.adapter = adapter
        binding.rvGameHistory.layoutManager = LinearLayoutManager(this)

        mGameHistoryViewModel = ViewModelProvider(this)[GameHistoryViewModel::class.java]

        mGameHistoryViewModel.readAllGameHistoryData().observe(this) { value ->
            Log.d("GameHistoryActivity", "Observer has received the updated data")
            adapter.setData(value)
        }

        val builder = AlertDialog.Builder(this)
            .setTitle("PERINGATAN")
            .setMessage("Apakah anda yakin ingin menghapus data? (Data yang terhapus tidak dapat dikembalikan)")
            .setPositiveButton("Ya") { _, _ ->
                mGameHistoryViewModel.deleteAllGameHistoryData()
            }
            .setNegativeButton("Tidak") { _, _ ->
            }
            .create()

        binding.btnClose.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))
        }

        binding.btnDeleteDataGameHistory.setOnClickListener {
            builder.show()
        }
    }
}