package com.jokeapplication.data.repository

import com.jokeapplication.data.model.JokesModel

class JokeRepository(
    private val jokeLocalDataSource: JokeLocalDataSource,
    private val jokeRemoteDataSource: JokeRemoteDataSource
) {
    suspend fun getJokesFromDB() = jokeLocalDataSource.getJokesFromDB()
    suspend fun getJokes() = jokeRemoteDataSource.getJoke()
    suspend fun saveJokeIntoDB(jokesModel: JokesModel) =
        jokeLocalDataSource.insertJokeIntoDB(jokesModel.toJokeEntity())
}