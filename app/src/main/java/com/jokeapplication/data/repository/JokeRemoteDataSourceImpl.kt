package com.jokeapplication.data.repository

import com.jokeapplication.data.model.JokesModel
import com.jokeapplication.presentation.network.ApiInterface
import retrofit2.Response

class JokeRemoteDataSourceImpl(private val apiInterface: ApiInterface) : JokeRemoteDataSource {
    override suspend fun getJoke(): Response<JokesModel> {
        return apiInterface.getJoke()
    }
}