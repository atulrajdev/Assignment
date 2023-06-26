package com.jokeapplication.data.model

import com.jokeapplication.presentation.room.JokesEntity

data class JokesModel(var joke: String?, var timeStamp: Long?) {
    fun toJokeEntity(): JokesEntity {
        val jokesEntity = JokesEntity()
        jokesEntity.joke = joke
        jokesEntity.timeStamp = timeStamp
        return jokesEntity
    }
}