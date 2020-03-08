package com.slepnev.rockpaperscissors.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.slepnev.rockpaperscissors.model.Game


//database holder class, main access point to the db data
@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class GameHistoryRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAME_HISTORY_DB"

        //Singleton object creation:
        @Volatile
        private var gameHistoryRoomDatabaseInstance: GameHistoryRoomDatabase? = null

        fun getDatabase(context: Context): GameHistoryRoomDatabase? {
            // if the instance of the database doesn't exist, create a new one:
            if (gameHistoryRoomDatabaseInstance == null) {
                synchronized(GameHistoryRoomDatabase::class.java) {
                    if (gameHistoryRoomDatabaseInstance == null) {
                        gameHistoryRoomDatabaseInstance = Room.databaseBuilder(context.applicationContext,
                            GameHistoryRoomDatabase::class.java,
                            DATABASE_NAME).build()
                    }
                }
            }
            // otherwise, return the existing one:
            return gameHistoryRoomDatabaseInstance
        }
    }


}