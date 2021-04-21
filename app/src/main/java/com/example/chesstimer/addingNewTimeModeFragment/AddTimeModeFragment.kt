package com.example.chesstimer.addingNewTimeModeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.chesstimer.R
import com.example.chesstimer.databinding.FragmentAddingTimeModeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [AddTimeModeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AddTimeModeFragment : Fragment() {

    val viewModel: AddTimeModeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentAddingTimeModeBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_adding_time_mode, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.navigatedToSecondPlayer.observe(viewLifecycleOwner, { navigated ->
            if (navigated == false) {
                binding.root.findNavController()
                    .navigate(R.id.action_addingTimeMode_to_timeModesFragment)
            }
        })
        return binding.root
    }
}