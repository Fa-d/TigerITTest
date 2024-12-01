package com.example.tigerittest.domain.worker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.tigerittest.domain.utils.USERS_SYNC_WORKER

class UsersFetchReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val initialFetchWork = OneTimeWorkRequestBuilder<UsersSyncWorker>().build()
        WorkManager.getInstance(context)
            .beginUniqueWork(USERS_SYNC_WORKER, ExistingWorkPolicy.KEEP, initialFetchWork).enqueue()
    }

}