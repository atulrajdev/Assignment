package com.jokeapplication.data.repository

import androidx.lifecycle.LiveData
import com.jokeapplication.presentation.room.AppDatabase
import com.jokeapplication.presentation.room.JokesEntity

class JokeLocalDataSourceImpl(private val appDatabase: AppDatabase) : JokeLocalDataSource {
    override suspend fun getJokesFromDB(): LiveData<List<JokesEntity>> {
        return appDatabase.getJokesDao().getAll()
    }

    override suspend fun insertJokeIntoDB(jokesEntity: JokesEntity): Long {
        return appDatabase.getJokesDao().insert(jokesEntity)
    }
}