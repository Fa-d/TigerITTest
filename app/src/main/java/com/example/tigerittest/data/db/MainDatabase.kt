package com.example.tigerittest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tigerittest.data.models.Post
import com.example.tigerittest.data.models.RemoteKeys
import com.example.tigerittest.data.models.User

@Database(
    entities = [Post::class, RemoteKeys::class, User::class], version = 2, exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun usersDao(): UsersDao

}