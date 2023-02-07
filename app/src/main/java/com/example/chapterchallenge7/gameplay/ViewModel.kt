package com.example.chapterchallenge7.gameplay

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

class ViewModel(application: Application): AndroidViewModel(application) {

    fun createSharedPreferences(name: String): SharedPreferences {
        return SharedPreferences(name, getApplication())
    }



    fun getName(player: SharedPreferences, defaultValue: String): String? {
        return player.getString("NAME", defaultValue)
    }

    fun setName(player: SharedPreferences, name: String) {
        player.saveString("NAME", name)
    }

    fun setItem(player: SharedPreferences, item: String) {
        player.saveString("ITEM", item)
    }

    fun getItem(player: SharedPreferences): String? {
        return player.getString("ITEM", "BATU")
    }

    fun addScore(player: SharedPreferences) {
        val currentScore = player.getInt("SCORE", 0)
        val updatedScore = currentScore?.plus(1)
        if (updatedScore != null) {
            player.saveInt("SCORE", updatedScore)
        }
    }

    fun resetScore(player: SharedPreferences) {
        player.saveInt("SCORE", 0)
    }

    fun getScore(player: SharedPreferences): Int? {
        return player.getInt("SCORE",0)
    }







}