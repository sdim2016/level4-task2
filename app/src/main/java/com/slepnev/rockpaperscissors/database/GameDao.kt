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
}