package com.example.tigerittest.core

import com.example.tigerittest.api.ApiService
import com.example.tigerittest.db.MainDatabase
import com.example.tigerittest.models.Post
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val network: ApiService, private val database: MainDatabase
) {
    suspend fun getUserByIdNetwork(userId: Int) = network.getUserById(userId)
    suspend fun getPostsByOffset(offset: Int = 0, limit: Int = 10) =
        network.getPosts(skip = offset, limit = limit)

    suspend fun insertPostsToDb(posts: List<Post>) = database.postsDao().insertAll(posts)

}