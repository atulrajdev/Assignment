package com.jokeapplication.presentation.di

import androidx.room.Room
import com.jokeapplication.BuildConfig
import com.jokeapplication.data.repository.*
import com.jokeapplication.data.usecases.GetLocalJokeUseCase
import com.jokeapplication.data.usecases.GetRemoteJokeUseCase
import com.jokeapplication.data.usecases.SaveLocalJokeUseCase
import com.jokeapplication.presentation.network.ApiInterface
import com.jokeapplication.presentation.room.AppDatabase
import com.jokeapplication.presentation.utils.Constants
import com.jokeapplication.presentation.viewmodel.JokesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<ApiInterface> {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "assignment_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
            .build()
    }

    factory<JokeLocalDataSource> { JokeLocalDataSourceImpl(get()) }
    factory<JokeRemoteDataSource> { JokeRemoteDataSourceImpl(get()) }

    factory { JokeRepository(get(), get()) }

    factory { GetLocalJokeUseCase(get()) }
    factory { GetRemoteJokeUseCase(get()) }
    factory { SaveLocalJokeUseCase(get()) }

    viewModel { JokesViewModel(get(), get(), get()) }
}

