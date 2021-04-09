package com.example.chesstimer

import dagger.hilt.android.HiltAndroidApp
import android.app.Application
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.chesstimer.onInstallWork.InsertDefaultDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class ChessTimerApplication: Application(){

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init(){
        applicationScope.launch {
            setupOneTimeWork()
        }
    }

    private fun setupOneTimeWork(){
        val oneTimeRequest = OneTimeWorkRequestBuilder<InsertDefaultDataWorker>()
            .build()
        WorkManager.getInstance().enqueue(oneTimeRequest)
    }
}