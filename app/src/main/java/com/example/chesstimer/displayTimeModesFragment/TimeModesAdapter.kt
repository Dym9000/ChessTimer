package com.example.chesstimer.displayTimeModesFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstimer.databinding.ListTimeModesItemBinding
import com.example.chesstimer.timeModesDatabase.TimeMode
import com.example.chesstimer.timeModesDatabase.TimeModeWithPlayers
import java.util.*

class TimeModesAdapter(private val clickListener: OnTimeModeClickListener) :
    ListAdapter<TimeModeWithPlayers, TimeModesAdapter.TimeModeViewHolder>(TimeModeDiffCallback()) {

    fun getItemAtPosition(position: Int): TimeModeWithPlayers {
        return getItem(position)
    }

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        val dataList = currentList
        if (fromPosition < toPosition) {
            for (i in fromPosition until (toPosition - 1) step 1) {
                Collections.swap(currentList, i, i + 1)
            }
        } else {
            for (i in toPosition until (fromPosition - 1) step 1) {
                Collections.swap(currentList, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeModeViewHolder {
        return TimeModeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TimeModeViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class TimeModeViewHolder private constructor(val binding: ListTimeModesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TimeModeWithPlayers, clickListener: OnTimeModeClickListener) {
            binding.clickListener = clickListener
            binding.item = item
            binding.itemTime = item.singleTimeMode
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TimeModeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListTimeModesItemBinding.inflate(layoutInflater, parent, false)
                return TimeModeViewHolder(binding)
            }
        }
    }
}

class TimeModeDiffCallback : DiffUtil.ItemCallback<TimeModeWithPlayers>() {
    override fun areItemsTheSame(
        oldItem: TimeModeWithPlayers,
        newItem: TimeModeWithPlayers
    ): Boolean {
        return oldItem.singleTimeMode == newItem.singleTimeMode
    }

    override fun areContentsTheSame(
        oldItem: TimeModeWithPlayers,
        newItem: TimeModeWithPlayers
    ): Boolean {
        return oldItem.players == newItem.players
    }
}

class OnTimeModeClickListener(val clickListener: (timeMode: TimeMode) -> Unit) {
    fun onClick(timeModeWithPlayers: TimeModeWithPlayers) =
        clickListener(timeModeWithPlayers.singleTimeMode)
}