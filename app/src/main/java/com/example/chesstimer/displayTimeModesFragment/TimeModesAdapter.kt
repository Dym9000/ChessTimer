package com.example.chesstimer.displayTimeModesFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstimer.databinding.ListTimeModesItemBinding
import com.example.chesstimer.timeModesDatabase.TimeMode
import com.example.chesstimer.timeModesDatabase.TimeModeWithPlayers

class TimeModesAdapter(val clickListener: OnTimeModeClickListener) :
    ListAdapter<TimeModeWithPlayers, TimeModesAdapter.TimeModeViewHolder>(TimeModeDiffCallback()) {

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