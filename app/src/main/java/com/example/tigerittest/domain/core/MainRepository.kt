package com.example.tigerittest.domain.core

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tigerittest.data.network.ApiService
import com.example.tigerittest.data.db.MainDatabase
import com.example.tigerittest.data.models.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val network: ApiService, private val database: MainDatabase
) {
    suspend fun getUserByIdNetwork(userId: Int) = network.getUserById(userId)
    suspend fun getPostsByOffset(offset: Int = 0, limit: Int = 10) =
        network.getPosts(skip = offset, limit = limit)

    suspend fun insertPostsToDb(posts: List<Post>) = database.postsDao().insertAll(posts)


    fun getPostsFromMediator(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = PostsRemoteMediator(database, network),
            pagingSourceFactory = {
                database.postsDao().getAllPosts()
            },
        ).flow
    }
}