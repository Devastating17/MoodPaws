package com.example.moodpaws.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Database(entities = [Mood::class, DiaryEntry::class], version = 2)
    abstract fun moodDao(): MoodDao
    abstract fun diaryDao(): DiaryDao

@Entity(tableName = "diary_entries")
data class DiaryEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val content: String,
    val mood: String? = null
)
