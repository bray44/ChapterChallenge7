package com.example.chapterchallenge7.gamehistory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_history_table")
data class GameHistory(
    val playerOneName: String,
    val playerOneItem: Int,
    val playerTwoName: String,
    val playerTwoItem: Int,
    val gameResult: String
)