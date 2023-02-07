package com.example.chapterchallenge7.gamehistory.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.GameHistoryItemBinding

class GameHistoryAdapter: RecyclerView.Adapter<GameHistoryAdapter.MyViewHolder>() {

    private var itemList = emptyList<GameHistory>()

    inner class MyViewHolder(val binding: GameHistoryItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = GameHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.binding.apply {
            tvPlayerOneNameGameHistory.text = currentItem.playerOneName
            ivPlayerOneItemGameHistory.setImageResource(currentItem.playerOneItem)
            tvPlayerTwoNameGameHistory.text = currentItem.playerTwoName
            ivPlayerTwoItemGameHistory.setImageResource(currentItem.playerTwoItem)
            tvGameResultGameHistory.text = currentItem.gameResult
        }
    }

    fun setData(value: List<GameHistory>) {
        this.itemList = value
        notifyDataSetChanged()
    }
}