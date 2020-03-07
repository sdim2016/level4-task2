package com.slepnev.rockpaperscissors.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.slepnev.rockpaperscissors.R
import com.slepnev.rockpaperscissors.model.Game

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        ivRock.setOnClickListener { playOneRound(0) }
        ivPaper.setOnClickListener { playOneRound(1) }
        ivScissors.setOnClickListener { playOneRound(2) }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun playOneRound(playersChoice: Int) {
        val computersChoice = (0..2).random() //generating the computer's answer
        ivComputer.setImageResource(Game.GAME_RES_DRAWABLES_IDS[computersChoice])
        ivYou.setImageResource(Game.GAME_RES_DRAWABLES_IDS[playersChoice])
    }

}
