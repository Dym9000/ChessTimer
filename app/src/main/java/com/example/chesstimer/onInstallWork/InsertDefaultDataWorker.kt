package com.example.chesstimer.onInstallWork

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.chesstimer.timeModesDatabase.TimeMode
import com.example.chesstimer.timeModesDatabase.TimeModesDAO
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class InsertDefaultDataWorker @AssistedInject constructor(
    @Assisted appContext: Context, @Assisted params:WorkerParameters, private val dao:TimeModesDAO)
    : CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "com.example.chesstimer.onInstallWork"
        val blitzMode = TimeMode(1,5L,0L,0L,5L,0L)
        val fastMode = TimeMode(2,5L,0L,0L,5L,0L)
    }

    override suspend fun doWork(): Result {
        try {
            dao.insert(blitzMode)
            dao.insert(fastMode)
        }
        catch (exception:Exception){
            return Result.failure()
        }
        return Result.success()
    }

}