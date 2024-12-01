package com.example.tigerittest.core

import com.example.tigerittest.api.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val network: ApiService
) {
    suspend fun getUserByIdNetwork(userId: Int) = network.getUserById(userId)
    suspend fun getPostsByOffset(offset: Int = 10, limit: Int = 10) =
        network.getPosts(skip = offset, limit = limit)

}