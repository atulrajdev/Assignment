package com.jokeapplication.presentation.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JokesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(jokesEntity: JokesEntity):Long

    @Delete
    fun delete(jokesEntity: JokesEntity)

    @Query("select * from JokesEntity ORDER BY timestamp DESC limit 10")
    fun getAll(): LiveData<List<JokesEntity>>
}