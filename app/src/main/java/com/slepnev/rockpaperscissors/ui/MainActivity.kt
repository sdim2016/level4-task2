package com.slepnev.rockpaperscissors.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.slepnev.rockpaperscissors.R
import com.slepnev.rockpaperscissors.database.GameRepository
import com.slepnev.rockpaperscissors.model.Game

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        gameRepository = GameRepository(this)

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
            R.id.action_history -> {
                startHistoryActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun playOneRound(playersChoice: Int) {
        /*
            choices:
            0 => ROCK
            1 => PAPER
            2 => SCISSORS

            results (for player):
            0 => lose
            1 => draw
            2 => win
         */

        val computersChoice = (0..2).random() //generating the computer's answer
        var result = -1

        ivComputer.setImageResource(Game.GAME_RES_DRAWABLES_IDS[computersChoice])
        ivYou.setImageResource(Game.GAME_RES_DRAWABLES_IDS[playersChoice])

        if (playersChoice == computersChoice) {result = 1} else {
            when (playersChoice) {
                0 -> {
                    when (computersChoice) {
                        1 -> result = 0
                        2 -> result = 2
                    }
                }
                1 -> {
                    when (computersChoice) {
                        0 -> result = 2
                        2 -> result = 0
                    }
                }
                2 -> {
                    when (computersChoice) {
                        0 -> result = 0
                        1 -> result = 2
                    }
                }
            }
        }

        when(result) {
            0 -> tvResult.text = "Computer wins!"
            1 -> tvResult.text = "Draw"
            2 -> tvResult.text = "You win!"
        }

        mainScope.launch {
            val game = Game(date = Date(), computer = computersChoice,
                player = playersChoice, result = result)
            withContext(Dispatchers.IO) {
                gameRepository.addGame(game)
            }
        }

    }

    private fun startHistoryActivity() {
        val intent = Intent(this, GameHistoryActivity::class.java)
        startActivity(intent)
    }

}
