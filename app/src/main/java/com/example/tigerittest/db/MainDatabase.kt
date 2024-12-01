package com.example.tigerittest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tigerittest.models.Post
import com.example.tigerittest.models.RemoteKeys
import com.example.tigerittest.models.User

@Database(
    entities = [Post::class, RemoteKeys::class, User::class], version = 2, exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun usersDao(): UsersDao

}