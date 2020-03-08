package com.slepnev.rockpaperscissors.database

import android.content.Context
import com.slepnev.rockpaperscissors.model.Game


//responsible for using the DAO to make operations on database
//instead of directly working with DAOs in activity classes, we will simply create a repository
//and work through its methods

class GameRepository(context: Context) {
    private val gameDao: GameDao

    init {
        val database = GameHistoryRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> = gameDao.getAllGames()

    suspend fun addGame(game: Game) = gameDao.addGame(game)

    suspend fun clearGameHistory() = gameDao.clearGameHistory()

    suspend fun getLossesCount(): Int = gameDao.getLossesCount()

    suspend fun getDrawsCount(): Int = gameDao.getDrawsCount()

    suspend fun getWinsCount(): Int = gameDao.getWinsCount()


}