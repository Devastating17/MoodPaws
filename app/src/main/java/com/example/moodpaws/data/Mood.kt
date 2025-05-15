package com.example.moodpaws.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_table")
data class Mood(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val moodType: String,
    val date:String,
    val note: String? = null
)