package com.slepnev.rockpaperscissors.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.slepnev.rockpaperscissors.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    suspend fun getAllGames():List<Game>

    @Insert
    suspend fun addGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun clearGameHistory()

    @Query("SELECT COUNT(*) FROM gameTable WHERE result = 0")
    suspend fun getLossesCount(): Int

    @Query("SELECT COUNT(*) FROM gameTable WHERE result = 1")
    suspend fun getDrawsCount(): Int

    @Query("SELECT COUNT(*) FROM gameTable WHERE result = 2")
    suspend fun getWinsCount(): Int
}