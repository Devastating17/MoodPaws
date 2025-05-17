package com.example.moodpaws.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiaryDao {

    @Insert
    suspend fun insertDiary(diary: DiaryEntry)

    @Query("SELECT * FROM diary_entries ORDER BY date DESC")
    suspend fun getAllDiaries(): List<DiaryEntry>
}
