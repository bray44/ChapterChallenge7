package com.example.chapterchallenge7.gamehistory.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameHistoryModelView(application: Application): AndroidViewModel(application) {

    private val readAllGameHistory: LiveData<List<GameHistory>>
    private val repository: GameHistoryRepository

    init {
        val gameHistoryDataDAO = GameHistoryDatabase.getDatabase(application).gameHistoryDataDAO()
        repository = GameHistoryRepository(gameHistoryDataDAO)
        readAllGameHistory = repository.readAllGameHistory
    }

    fun addGameHistory(gameHistory: GameHistory) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGameHistory(gameHistory)
        }
    }

}