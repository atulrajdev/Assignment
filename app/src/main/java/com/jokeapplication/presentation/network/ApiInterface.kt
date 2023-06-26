package com.jokeapplication.presentation.network

import com.jokeapplication.data.model.JokesModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/api?format=json")
    suspend fun getJoke(): Response<JokesModel>
}