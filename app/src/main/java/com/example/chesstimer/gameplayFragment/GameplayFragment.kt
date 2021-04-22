package com.example.chesstimer.gameplayFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.chesstimer.R
import com.example.chesstimer.databinding.FragmentGameplayBinding
import com.example.chesstimer.displayTimeModesFragment.TimeModesRepository
import com.example.chesstimer.utils.CountdownChessTimer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameplayFragment : Fragment() {

    @Inject
    lateinit var repository: TimeModesRepository

    @Inject
    lateinit var counter: CountdownChessTimer

    private val gameplayArgs: GameplayFragmentArgs by navArgs()

    private val gameplayViewModel: GameplayViewModel by viewModels {
        GameplayViewModelFactory(gameplayArgs.timeModeId, repository, counter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentGameplayBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_gameplay, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = gameplayViewModel
        }

        setSubscribers()

        return binding.root
    }

    private fun setSubscribers() {
        gameplayViewModel.timeLeftInMillisPlayer1.observe(viewLifecycleOwner, {
            gameplayViewModel.onTime1Changed(it)
        })

        gameplayViewModel.timeLeftInMillisPlayer2.observe(viewLifecycleOwner, {
            gameplayViewModel.onTime2Changed(it)
        })
    }

}
