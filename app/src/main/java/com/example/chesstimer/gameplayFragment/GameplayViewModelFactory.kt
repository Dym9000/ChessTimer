package com.example.chesstimer.gameplayFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chesstimer.displayTimeModesFragment.TimeModesRepository
import com.example.chesstimer.utils.CountdownChessTimer

class GameplayViewModelFactory(
    private val timeModeKey: Int,
    private val repository: TimeModesRepository,
    private val counter: CountdownChessTimer
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameplayViewModel::class.java)) {
            return GameplayViewModel(timeModeKey, repository, counter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}