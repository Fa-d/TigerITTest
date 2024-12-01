package com.example.tigerittest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tigerittest.models.User

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(user: List<User>)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertSingleUser(user: User)

    @Query("DELETE FROM users")
    suspend fun clearAllUsers()

}