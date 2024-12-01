package com.example.tigerittest

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Intent
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.tigerittest.domain.utils.USERS_SYNC_WORKER
import com.example.tigerittest.domain.worker.UsersFetchReceiver
import com.example.tigerittest.domain.worker.UsersSyncWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MainApp : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG).build()

    override fun onCreate() {
        super.onCreate()
        scheduleUserFetch()
    }

    fun scheduleUserFetch() {
        val initialFetchWork = OneTimeWorkRequestBuilder<UsersSyncWorker>().build()
        WorkManager.getInstance(this)
            .beginUniqueWork(USERS_SYNC_WORKER, ExistingWorkPolicy.KEEP, initialFetchWork).enqueue()
        scheduleAlarm()
    }

    fun scheduleAlarm() {
        val intent = Intent(this, UsersFetchReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1 * 60 * 1000, pendingIntent
        )

    }
}