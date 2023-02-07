package com.example.chapterchallenge7.gamehistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivityGameHistoryBinding
import com.example.chapterchallenge7.gamehistory.data.GameHistoryAdapter

class GameHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameHistoryBinding
    private lateinit var mGameHistoryModelView: GameHistoryModelView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = GameHistoryAdapter()
        binding.rvGameHistory.adapter = adapter
        binding.rvGameHistory.layoutManager = LinearLayoutManager(this)

        mGameHistoryModelView = ViewModelProvider(this)[GameHistoryModelView::class.java]


        mGameHistoryModelView.addGameHistory(
            "adadadada",
            R.drawable.ic_batu,
            "asdasdasdas",
            R.drawable.ic_gunting,
            "adsadasdasdas"
        )
        mGameHistoryModelView.addGameHistory(
            "adadadada",
            R.drawable.ic_batu,
            "asdasdasdas",
            R.drawable.ic_gunting,
            "adsadasdasdas"
        )
        mGameHistoryModelView.addGameHistory(
            "adadadada",
            R.drawable.ic_batu,
            "asdasdasdas",
            R.drawable.ic_gunting,
            "adsadasdasdas"
        )

        mGameHistoryModelView.readAllGameHistoryData().observe(this, Observer { value ->
            adapter.setData(value)
        })

    }


}