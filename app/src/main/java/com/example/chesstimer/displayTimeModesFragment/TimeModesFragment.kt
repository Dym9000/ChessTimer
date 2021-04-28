package com.example.chesstimer.displayTimeModesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstimer.R
import com.example.chesstimer.databinding.FragmentTimeModesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimeModesFragment : Fragment() {

    private lateinit var adapter: TimeModesAdapter
    private val timeModesViewModel: TimeModesViewModel by viewModels()
    private lateinit var binding: FragmentTimeModesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_time_modes, container, false
        )
        binding.lifecycleOwner = this.viewLifecycleOwner

        adapter = TimeModesAdapter(OnTimeModeClickListener { item ->
            timeModesViewModel.onItemClicked(item)
        })
        binding.timeModes.adapter = adapter

        val manager = LinearLayoutManager(activity)
        binding.timeModes.layoutManager = manager

        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)

        setSubscribers()
        setClickListeners()
        setItemTouchHelper()

        return binding.root
    }

    private fun setSubscribers() {
        timeModesViewModel.clickedItemId.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController()
                    .navigate(
                        TimeModesFragmentDirections.actionTimeModesFragmentToGameplayFragment(
                            it
                        )
                    )
                timeModesViewModel.navigatedToItemClicked()
            }
        })

        timeModesViewModel.timeModes.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    private fun setClickListeners() {
        binding.addTimeMode.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(TimeModesFragmentDirections.actionTimeModesFragmentToAddingTimeMode())
        }
    }

    private fun setItemTouchHelper() {
        val itemInteractionHandler = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT
                    or ItemTouchHelper.RIGHT, ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                adapter.onItemMove(fromPosition, toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = adapter.getItemAtPosition(position)
                timeModesViewModel.onSwipe(item)
            }
        })
        itemInteractionHandler.attachToRecyclerView(binding.timeModes)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.clearAllData -> timeModesViewModel.onClearDataIconClicked()
        }
        ActivityCompat.recreate(requireActivity())
        return super.onOptionsItemSelected(item)
    }

}