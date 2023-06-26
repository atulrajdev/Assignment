package com.jokeapplication.data.repository

import androidx.lifecycle.LiveData
import com.jokeapplication.presentation.room.JokesEntity

interface JokeLocalDataSource {
    suspend fun getJokesFromDB(): LiveData<List<JokesEntity>>
    suspend fun insertJokeIntoDB(jokesEntity: JokesEntity): Long
}