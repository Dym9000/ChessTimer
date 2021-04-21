package com.example.chesstimer.displayTimeModesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chesstimer.R
import com.example.chesstimer.databinding.FragmentTimeModesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimeModesFragment : Fragment() {

    private val timeModesViewModel: TimeModesViewModel by viewModels()
//    lateinit var adapter: TimeModesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentTimeModesBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_time_modes, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner

        val adapter = TimeModesAdapter(OnTimeModeClickListener { item ->
            timeModesViewModel.onItemClicked(item)
        })
        binding.timeModes.adapter = adapter

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

        val manager = LinearLayoutManager(activity)
        binding.timeModes.layoutManager = manager

        binding.addTimeMode.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(TimeModesFragmentDirections.actionTimeModesFragmentToAddingTimeMode())
        }


        return binding.root
    }

}