package com.example.tigerittest.domain.core

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.MediatorResult
import androidx.room.withTransaction
import com.example.tigerittest.data.db.MainDatabase
import com.example.tigerittest.data.models.Post
import com.example.tigerittest.data.models.RemoteKeys
import com.example.tigerittest.data.network.ApiService
import com.example.tigerittest.domain.utils.POSTS_REMOTE_ID
import javax.inject.Inject

class PostsRemoteMediator @Inject constructor(
    private val mainDb: MainDatabase, private val apiService: ApiService
) : RemoteMediator<Int, Post>() {


    override suspend fun load(loadType: LoadType, state: PagingState<Int, Post>): MediatorResult {

        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> {
                    val lastSetOffset =
                        mainDb.remoteKeysDao().remoteKeysId(POSTS_REMOTE_ID)?.nextOffset ?: 0
                    if (lastSetOffset > 10) return MediatorResult.Success(endOfPaginationReached = true) else 0
                }

                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey =
                        mainDb.remoteKeysDao().remoteKeysId(POSTS_REMOTE_ID) ?: RemoteKeys(
                            POSTS_REMOTE_ID, 0
                        )
                    if (remoteKey.nextOffset == 0) return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                    remoteKey.nextOffset
                }
            }

            val apiResponse = apiService.getPosts(skip = offset, limit = state.config.pageSize)
            val results = apiResponse.posts

            val nextOffset = if (results.isNotEmpty()) results.last().id + 1 else 0

            mainDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    mainDb.postsDao().clearPosts()
                    mainDb.remoteKeysDao().clearRemoteKeys(POSTS_REMOTE_ID)
                }
                mainDb.postsDao().insertAll(results)
                mainDb.remoteKeysDao().insert(
                    RemoteKeys(remoteKeyId = POSTS_REMOTE_ID, nextOffset = nextOffset)
                )
            }
            MediatorResult.Success(endOfPaginationReached = results.size < state.config.pageSize)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
