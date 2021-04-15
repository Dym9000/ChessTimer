package com.example.chesstimer.timeModesListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chesstimer.R
import com.example.chesstimer.databinding.FragmentTimeModesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TimeModesFragment : Fragment() {

    private val timeModesViewModel:TimeModesViewModel by viewModels()
    @Inject lateinit var adapter: TimeModesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentTimeModesBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_time_modes, container, false)
        binding.lifecycleOwner = this

        binding.timeModes.adapter = adapter

        timeModesViewModel.timeModes.observe(viewLifecycleOwner,{
            it?.let {
                adapter.submitList(it)
            } ?: Toast.makeText(activity, "List is empty", Toast.LENGTH_LONG).show()
        })

        val manager = LinearLayoutManager(activity)
        binding.timeModes.layoutManager = manager

        binding.addTimeMode.setOnClickListener {view: View ->
            view.findNavController().navigate(TimeModesFragmentDirections.actionTimeModesFragmentToAddingTimeMode())
        }


        return binding.root
    }

}