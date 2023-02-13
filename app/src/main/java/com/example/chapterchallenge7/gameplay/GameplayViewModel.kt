package com.example.chapterchallenge7.gameplay

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class GameplayViewModel(application: Application): AndroidViewModel(application) {

    // This variable purpose is to give data to Sound Effect Winner
    var playerOneWin = true


    fun createGameplayData(name: String): GameplayData {
        return GameplayData(name, getApplication())
    }
    fun getName(player: GameplayData, defaultValue: String): String? {
        return player.getString("NAME", defaultValue)
    }
    fun setName(player: GameplayData, name: String) {
        player.saveString("NAME", name)
    }
    fun getItem(player: GameplayData): String? {
        return player.getString("ITEM", "BATU")
    }
    fun setItem(player: GameplayData, item: String) {
        player.saveString("ITEM", item)
    }
    fun getScore(player: GameplayData): Int? {
        return player.getInt("SCORE",0)
    }
    fun addScore(player: GameplayData) {
        val currentScore = player.getInt("SCORE", 0)
        val updatedScore = currentScore?.plus(1)
        if (updatedScore != null) {
            player.saveInt("SCORE", updatedScore)
        }

    }
    fun resetScore(player: GameplayData) {
        player.saveInt("SCORE", 0)
    }

    fun getPlayerOneResult(): Boolean {
        return playerOneWin
    }

    private fun isPlayerOneWin(value: Boolean) {
        playerOneWin = value
    }

    fun calculateResult(playerOne: GameplayData, playerTwo: GameplayData): String {

        val playerOneItem = getItem(playerOne)
        val playerTwoItem = getItem(playerTwo)
        val playerOneWinText = "${getName(playerOne, "Player 1")}\n MENANG!"
        val playerTwoWinText = "${getName(playerTwo, "Player 2")}\n MENANG!"

        return if (playerOneItem == playerTwoItem) {
            isPlayerOneWin(false)
            "DRAW!"
        } else if (playerOneItem == "KERTAS" && playerTwoItem == "BATU") {
            addScore(playerOne)
            isPlayerOneWin(true)
            playerOneWinText
        } else if (playerOneItem == "GUNTING" && playerTwoItem == "KERTAS") {
            addScore(playerOne)
            isPlayerOneWin(true)
            playerOneWinText
        } else if (playerOneItem == "BATU" && playerTwoItem == "GUNTING") {
            addScore(playerOne)
            isPlayerOneWin(true)
            playerOneWinText
        } else {
            addScore(playerTwo)
            isPlayerOneWin(false)
            playerTwoWinText
        }
    }







}