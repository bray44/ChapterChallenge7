package com.example.chapterchallenge7.gamehistory.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Interface berisi method method untuk mengubah player_data_table
@Dao
interface GameHistoryDataDAO {

    // Method utk menambah data ke tabel. On Conflict IGNORE yaitu apabila ada data sama diabaikan
    // dan tetap ditambahkan.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGameHistory(gameHistory: GameHistory) //fungsi dilakukan pada thread lain (coroutine)

    // Method untuk membaca list history player
    @Query("SELECT * FROM game_history_table")
    fun readAllGameHistoryData(): LiveData<List<GameHistory>>


}