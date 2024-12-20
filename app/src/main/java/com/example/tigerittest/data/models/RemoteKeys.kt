package com.example.tigerittest.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "remote_keys")
data class RemoteKeys(@PrimaryKey val remoteKeyId: String, val nextOffset: Int)