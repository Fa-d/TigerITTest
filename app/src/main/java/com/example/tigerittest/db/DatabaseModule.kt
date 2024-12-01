package com.example.tigerittest.db

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): MainDatabase = Room.databaseBuilder(
        context, MainDatabase::class.java, "dummy_post.db"
    ).addMigrations(MIGRATION_1_2).build()
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create the 'remote_keys' table
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `remote_keys` (
                `remoteKeyId` TEXT NOT NULL,
                `nextOffset` INTEGER NOT NULL,
                PRIMARY KEY(`remoteKeyId`)
            )
            """
        )

        // Create the 'users' table (if it doesn't exist)
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `users` (
                `age` INTEGER NOT NULL,
                `birthDate` TEXT NOT NULL,
                `bloodGroup` TEXT NOT NULL,
                `ein` TEXT NOT NULL,
                `email` TEXT NOT NULL,
                `eyeColor` TEXT NOT NULL,
                `firstName` TEXT NOT NULL,
                `gender` TEXT NOT NULL,
                `height` REAL NOT NULL,
                `id` INTEGER NOT NULL,
                `image` TEXT NOT NULL,
                `ip` TEXT NOT NULL,
                `lastName` TEXT NOT NULL,
                `macAddress` TEXT NOT NULL,
                `maidenName` TEXT NOT NULL,
                `password` TEXT NOT NULL,
                `phone` TEXT NOT NULL,
                `role` TEXT NOT NULL,
                `ssn` TEXT NOT NULL,
                `university` TEXT NOT NULL,
                `userAgent` TEXT NOT NULL,
                `username` TEXT NOT NULL,
                `weight` REAL NOT NULL,
                PRIMARY KEY(`id`)
            )
            """
        )
    }
}