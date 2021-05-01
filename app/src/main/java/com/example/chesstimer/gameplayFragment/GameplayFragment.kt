package com.example.chesstimer.gameplayFragment

import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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

        setHasOptionsMenu(false)

        setSubscribers()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    private fun setSubscribers() {
        gameplayViewModel.timeLeftInMillisPlayer1.observe(viewLifecycleOwner, {
            gameplayViewModel.onTime1Changed(it)
            if(it == 0L){
                    onGameOver()
                }
        })

        gameplayViewModel.timeLeftInMillisPlayer2.observe(viewLifecycleOwner, {
            gameplayViewModel.onTime2Changed(it)
            if(it == 0L){
                onGameOver()
            }
        })
    }

    private fun onGameOver(){
        if (Build.VERSION.SDK_INT >= 26) {
            (context?.getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(
                VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            (context?.getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(2000)
        }
    }
}
