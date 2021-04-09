package com.example.chesstimer.timeModes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstimer.databinding.ListTimeModesItemBinding
import com.example.chesstimer.timeModesDatabase.TimeMode
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class TimeModesAdapter@Inject constructor()
    : ListAdapter<TimeMode,TimeModesAdapter.TimeModeViewHolder>(TimeModeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeModeViewHolder {
        return TimeModeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TimeModeViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class TimeModeViewHolder private constructor(val binding: ListTimeModesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TimeMode) {
            binding.timeMode = item
            binding.player1 = player1
            binding.player2 = player2
            binding.executePendingBindings()
        }

        companion object {
            val player1 = 1
            val player2 = 2
            fun from(parent: ViewGroup): TimeModeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListTimeModesItemBinding.inflate(layoutInflater, parent, false)
                return TimeModeViewHolder(binding)
            }
        }
    }
}

class TimeModeDiffCallback: DiffUtil.ItemCallback<TimeMode>(){

    override fun areItemsTheSame(oldItem: TimeMode, newItem: TimeMode): Boolean {
        return oldItem.modeId == newItem.modeId
    }

    override fun areContentsTheSame(oldItem: TimeMode, newItem: TimeMode): Boolean {
        return oldItem == newItem
    }
}