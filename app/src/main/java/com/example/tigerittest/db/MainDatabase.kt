package com.example.tigerittest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tigerittest.models.Post

@Database(
    entities = [Post::class], version = 1, exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao


}