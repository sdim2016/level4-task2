package com.slepnev.rockpaperscissors.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slepnev.rockpaperscissors.R
import com.slepnev.rockpaperscissors.database.GameRepository
import com.slepnev.rockpaperscissors.model.Game

import kotlinx.android.synthetic.main.activity_game_history.*
import kotlinx.android.synthetic.main.content_game_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameHistoryActivity : AppCompatActivity() {

    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)
    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_history)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        gameRepository = GameRepository(this)

        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_clear -> {
                clearGameHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        rvHistory.layoutManager = LinearLayoutManager(this@GameHistoryActivity, RecyclerView.VERTICAL, false)
        rvHistory.adapter = gameAdapter
        rvHistory.addItemDecoration(DividerItemDecoration(this@GameHistoryActivity, DividerItemDecoration.VERTICAL))
        getGameHistory()
    }

    private fun getGameHistory() {
        mainScope.launch {
            val gameList = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@GameHistoryActivity.games.clear()
            this@GameHistoryActivity.games.addAll(gameList)
            this@GameHistoryActivity.games.reverse()
            this@GameHistoryActivity.gameAdapter.notifyDataSetChanged()
        }
    }

    private fun clearGameHistory() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.clearGameHistory()
            }
            getGameHistory()
            Toast.makeText(this@GameHistoryActivity, "The history was cleared", Toast.LENGTH_SHORT).show()
        }
    }

}
