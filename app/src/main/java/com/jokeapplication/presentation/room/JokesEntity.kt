package com.jokeapplication.presentation.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jokeapplication.data.model.JokesModel

@Entity
class JokesEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var joke: String? = null
    var timeStamp: Long? = null

    fun toJokeModel(): JokesModel {
        return JokesModel(joke, timeStamp)
    }
}