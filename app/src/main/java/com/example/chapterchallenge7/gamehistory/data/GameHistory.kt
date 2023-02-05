package com.example.chapterchallenge7.gamehistory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_history_table")
data class GameHistory(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val playerOneName: String,
    val playerOneItem: String,
    val playerTwoName: String,
    val playerTwoItem: String,
    val gameResult: String
)