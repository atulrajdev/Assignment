package com.jokeapplication.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jokeapplication.data.usecases.GetLocalJokeUseCase
import com.jokeapplication.data.usecases.GetRemoteJokeUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TestViewModel : ViewModel(), KoinComponent {
//    private val apiInterface: ApiInterface by inject()
    private val getLocalJokeUseCase: GetLocalJokeUseCase by inject()
    private val getRemoteJokeUseCase: GetRemoteJokeUseCase by inject()
//    private val saveLocalJokeUseCase: SaveLocalJokeUseCase by inject()

    suspend fun showPrint() {
        Log.e(">>>>", "showPrint()")
        getLocalJokeUseCase.invoke()
        getRemoteJokeUseCase.invoke()
    }
}