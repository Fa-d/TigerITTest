package com.example.tigerittest.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tigerittest.data.models.Post

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(remoteKey: List<Post>)

    @Query("DELETE FROM posts")
    suspend fun clearPosts()


    @Query("SELECT * FROM posts")
    fun getAllPosts(): PagingSource<Int, Post>

    @Query("SELECT * FROM posts WHERE id = :postId")
    suspend fun getPostById(postId: Int): Post?

    @Query("SELECT * FROM posts WHERE userId = :userId")
    suspend fun getAllPostOfAUser(userId: Int): List<Post>

}