package com.jokeapplication.data.usecases

import com.jokeapplication.data.repository.JokeRepository

class GetRemoteJokeUseCase(private val jokeRepository: JokeRepository) {
    suspend operator fun invoke() = jokeRepository.getJokes()
}