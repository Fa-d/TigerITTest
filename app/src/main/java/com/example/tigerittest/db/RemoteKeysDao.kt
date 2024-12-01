package com.example.tigerittest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tigerittest.models.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(remoteKey: RemoteKeys)

    @Query("SELECT * FROM remote_keys WHERE remoteKeyId = :keyId")
    suspend fun remoteKeysId(keyId: String): RemoteKeys?

    @Query("DELETE FROM remote_keys WHERE remoteKeyId = :keyId")
    suspend fun clearRemoteKeys(keyId: String)
}
