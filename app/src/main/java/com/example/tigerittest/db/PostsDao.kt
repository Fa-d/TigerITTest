package com.example.tigerittest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tigerittest.models.Post

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(remoteKey: List<Post>)

    @Query("DELETE FROM posts")
    suspend fun clearPosts()

}