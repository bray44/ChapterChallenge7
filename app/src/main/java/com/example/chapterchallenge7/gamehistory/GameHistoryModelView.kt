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

class GameHistoryModelView(application: Application) : AndroidViewModel(application) {

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
        playerTwoName: String,
        playerTwoItem: Int,
        gameResult: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            gameHistoryDataDAO.addGameHistory(
                GameHistory(
                    playerOneName = playerOneName,
                    playerOneItem = playerOneItem,
                    playerTwoName = playerTwoName,
                    playerTwoItem = playerTwoItem,
                    gameResult = gameResult
                )
            )
        }
    }

    fun readAllGameHistoryData(): LiveData<List<GameHistory>> {
        return gameHistoryDataDAO.readAllGameHistoryData()
    }

}