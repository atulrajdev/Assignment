package com.jokeapplication.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokeapplication.data.usecases.GetLocalJokeUseCase
import com.jokeapplication.data.usecases.GetRemoteJokeUseCase
import com.jokeapplication.data.usecases.SaveLocalJokeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokesViewModel(
    private val getLocalJokeUseCase: GetLocalJokeUseCase,
    private val getRemoteJokeUseCase: GetRemoteJokeUseCase,
    private val saveLocalJokeUseCase: SaveLocalJokeUseCase
) : ViewModel() {

    suspend fun getJokesFromDB() = getLocalJokeUseCase.invoke()

    fun getRemoteJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getRemoteJokeUseCase.invoke()
                if (response.isSuccessful) {
                    response.body()?.let { jokeModel ->
                        jokeModel.timeStamp = System.currentTimeMillis()
                        val id = saveLocalJokeUseCase.invoke(jokeModel)
                        Log.d(">>>>>", "Inserted id ::$id")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}