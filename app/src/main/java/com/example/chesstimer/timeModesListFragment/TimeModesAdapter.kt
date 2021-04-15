package com.example.chesstimer.timeModesListFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstimer.databinding.ListTimeModesItemBinding
import com.example.chesstimer.timeModesDatabase.TimeModeWithPlayers
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class TimeModesAdapter@Inject constructor()
    : ListAdapter<TimeModeWithPlayers,TimeModesAdapter.TimeModeViewHolder>(TimeModeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeModeViewHolder {
        return TimeModeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TimeModeViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class TimeModeViewHolder private constructor(val binding: ListTimeModesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TimeModeWithPlayers) {
            binding.item = item
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

class TimeModeDiffCallback: DiffUtil.ItemCallback<TimeModeWithPlayers>(){
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
        return oldItem.players == oldItem.players
    }
}