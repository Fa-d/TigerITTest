package com.example.tigerittest.api

import com.example.tigerittest.models.PostsRes
import com.example.tigerittest.models.User
import com.example.tigerittest.models.UsersRes
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    suspend fun getPosts(
        @Query("limit") limit: Int, @Query("skip") skip: Int
    ): PostsRes

    @GET("users")
    suspend fun getUsers(
        @Query("limit") limit: Int, @Query("skip") skip: Int
    ): UsersRes

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): User

}