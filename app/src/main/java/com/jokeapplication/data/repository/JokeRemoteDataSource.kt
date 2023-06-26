package com.jokeapplication.data.repository

import com.jokeapplication.data.model.JokesModel
import retrofit2.Response

interface JokeRemoteDataSource {
    suspend fun getJoke(): Response<JokesModel>
}