package com.example.moodpaws.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMood(mood: Mood)

    @Query("SELECT * FROM mood_table ORDER BY date DESC")
    fun getAllMoodsLive(): LiveData<List<Mood>>

    @Query("SELECT * FROM mood_table WHERE date >= :startDate ORDER BY date DESC")
    suspend fun getMoodsSince(startDate: String): List<Mood>

    @Query("SELECT * FROM mood_table WHERE strftime('%m', date) = :month AND strftime('%Y', date) = :year ORDER BY date DESC")
    suspend fun getMoodsInMonth(month: String, year: String): List<Mood>

    @Query("SELECT * FROM mood_table WHERE strftime('%Y', date) = :year ORDER BY date DESC")
    suspend fun getMoodsInYear(year: String): List<Mood>
}


//TODO: LAST TWO QUESRIES MUST BE YYYY-MM-DD