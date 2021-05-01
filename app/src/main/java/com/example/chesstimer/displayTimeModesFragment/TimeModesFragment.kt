package com.example.chesstimer.displayTimeModesFragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
    private val onClearingDataDialog: AlertDialog by lazy { setClearingDataDialog() }

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
        setOnClickListeners()
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

    private fun setClearingDataDialog(): AlertDialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.clearing_all_data_dialog_title)
            .setMessage(R.string.clearing_all_data_dialog_text)
            .setPositiveButton(R.string.yes) { _, _ ->
                timeModesViewModel.onClearDataIconClicked()
            }
            .setNegativeButton(R.string.no) { _, _ ->
                Toast.makeText(requireContext(), "Data not deleted", Toast.LENGTH_SHORT).show()
            }
            .create()
    }

    private fun setOnClickListeners() {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        timeModesViewModel.timeModes.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                menu.findItem(R.id.clearAllData).isEnabled = false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clearAllData -> onClearingDataDialog.show()
        }
        return super.onOptionsItemSelected(item)
    }
}