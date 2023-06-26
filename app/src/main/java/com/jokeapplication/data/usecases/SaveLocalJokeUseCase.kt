package com.jokeapplication.data.usecases

import com.jokeapplication.data.model.JokesModel
import com.jokeapplication.data.repository.JokeRepository

class SaveLocalJokeUseCase(private val jokesRepository: JokeRepository) {
    suspend fun invoke(jokesModel: JokesModel) = jokesRepository.saveJokeIntoDB(jokesModel)
}