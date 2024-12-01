package com.example.tigerittest.domain.worker

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import androidx.hilt.work.HiltWorker
import androidx.room.withTransaction
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.tigerittest.data.network.ApiService
import com.example.tigerittest.data.db.MainDatabase
import com.example.tigerittest.data.models.RemoteKeys
import com.example.tigerittest.domain.utils.USERS_REMOTE_ID

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class UsersSyncWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val apiService: ApiService,
    private val mainDb: MainDatabase
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {

        val postsRes = apiService.getUsers(
            skip = mainDb.remoteKeysDao().remoteKeysId(USERS_REMOTE_ID)?.nextOffset ?: 0, limit = 10
        )

        if (postsRes.limit == 0) {
            val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, UsersFetchReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            alarmManager.cancel(pendingIntent)
            return Result.success()
        }
        val results = postsRes.users
        val nextOffset = results[0].id + 10
        mainDb.withTransaction {
            mainDb.usersDao().insert(results)
            mainDb.remoteKeysDao().insert(
                RemoteKeys(remoteKeyId = USERS_REMOTE_ID, nextOffset = nextOffset)
            )
        }
        return Result.success()
    }
}