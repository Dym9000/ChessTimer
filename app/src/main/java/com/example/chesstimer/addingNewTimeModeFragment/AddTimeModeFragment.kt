package com.example.chesstimer.addingNewTimeModeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.chesstimer.R
import com.example.chesstimer.databinding.FragmentAddingTimeModeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTimeModeFragment : Fragment() {

    private val viewModel: AddTimeModeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).invalidateOptionsMenu()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().invalidateOptionsMenu()

        val binding: FragmentAddingTimeModeBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_adding_time_mode, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)

        viewModel.navigatedToSecondPlayer.observe(viewLifecycleOwner, { navigated ->
            if (navigated == false) {
                binding.root.findNavController()
                    .navigate(R.id.action_addingTimeMode_to_timeModesFragment)
            }
        })
        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.clearAllData).isVisible = false
    }

}