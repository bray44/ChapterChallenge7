package com.example.chapterchallenge7.gamehistory.data

import androidx.lifecycle.LiveData

class GameHistoryRepository(private val gameHistoryDataDAO: GameHistoryDataDAO) {

    val readAllGameHistory: LiveData<List<GameHistory>> = gameHistoryDataDAO.readAllGameHistoryData()

    suspend fun addGameHistory(gameHistory: GameHistory) {
        gameHistoryDataDAO.addGameHistory(gameHistory)
    }



}