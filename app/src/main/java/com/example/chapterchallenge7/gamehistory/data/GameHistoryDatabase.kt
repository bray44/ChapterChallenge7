package com.example.chapterchallenge7.gamehistory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [GameHistory::class], version = 1, exportSchema = false)
abstract class GameHistoryDatabase: RoomDatabase() {

    abstract fun gameHistoryDataDAO(): GameHistoryDataDAO

}