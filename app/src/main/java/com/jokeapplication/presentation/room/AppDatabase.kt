package com.jokeapplication.presentation.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JokesEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getJokesDao(): JokesDao
}