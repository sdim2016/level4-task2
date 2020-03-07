package com.slepnev.rockpaperscissors.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.slepnev.rockpaperscissors.R
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "gameTable")
data class Game (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "computer")
    var computer: String,

    @ColumnInfo(name = "player")
    var player: String,

    @ColumnInfo(name = "result")
    var result: String

) : Parcelable {
    companion object {
        val GAME_OPTIONS = arrayOf("rock", "paper", "scissors")
        val GAME_RES_DRAWABLES_IDS = arrayOf(R.drawable.rock, R.drawable.paper, R.drawable.scissors)
    }
}