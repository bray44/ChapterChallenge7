package com.example.chapterchallenge7.gamehistory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.chapterchallenge7.gamehistory.data.GameHistory
import com.example.chapterchallenge7.gamehistory.data.GameHistoryDataDAO
import com.example.chapterchallenge7.gamehistory.data.GameHistoryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameHistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val gameHistoryDataDAO: GameHistoryDataDAO by lazy {
        Room.databaseBuilder(
            application,
            GameHistoryDatabase::class.java,
            "game_history"
        ).build().gameHistoryDataDAO()
    }

    fun addGameHistory(
        playerOneName: String,
        playerOneItem: Int,
        playerOneScore: String,
        playerTwoName: String,
        playerTwoItem: Int,
        playerTwoScore: String,
        gameResult: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            gameHistoryDataDAO.addGameHistory(
                GameHistory(
                    playerOneName = playerOneName,
                    playerOneItem = playerOneItem,
                    playerOneScore = playerOneScore,
                    playerTwoName = playerTwoName,
                    playerTwoItem = playerTwoItem,
                    playerTwoScore = playerTwoScore,
                    gameResult = gameResult
                )
            )
        }
    }

    fun readAllGameHistoryData(): LiveData<List<GameHistory>> {
        return gameHistoryDataDAO.readAllGameHistoryData()
    }

    fun deleteAllGameHistoryData() {
        viewModelScope.launch(Dispatchers.IO) {
            gameHistoryDataDAO.deleteAllGameHistoryData()
        }
    }

}