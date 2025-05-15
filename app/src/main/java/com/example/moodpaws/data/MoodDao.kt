package com.example.moodpaws.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mood: Mood)

    @Query("SELECT * FROM mood_table ORDER BY date DESC")
    fun getAllMods(): LiveData<List<Mood>>
}