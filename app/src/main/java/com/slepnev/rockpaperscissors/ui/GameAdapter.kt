package com.slepnev.rockpaperscissors.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slepnev.rockpaperscissors.R
import com.slepnev.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.item_history.view.*

class GameAdapter(private val games:List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            when (game.result) {
                0 -> itemView.tvResult.text = "Computer wins!"
                1 -> itemView.tvResult.text = "Draw"
                2 -> itemView.tvResult.text = "You win!"
            }
            itemView.ivYou.setImageResource(Game.GAME_RES_DRAWABLES_IDS[game.player])
            itemView.ivComputer.setImageResource(Game.GAME_RES_DRAWABLES_IDS[game.computer])
            itemView.tvDate.text = game.date.toString()
        }
    }
}