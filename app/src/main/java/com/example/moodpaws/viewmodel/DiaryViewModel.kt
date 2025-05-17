package com.example.moodpaws.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodpaws.data.DiaryEntry
import com.example.moodpaws.data.DiaryRepository
import com.example.moodpaws.data.MoodDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DiaryViewModel(application: Application) : AndroidViewModel(application) {

    private val diaryDao = MoodDatabase.getDatabase(application).diaryDao()
    private val repository = DiaryRepository(diaryDao)

    fun insertDiary(entry: DiaryEntry) = viewModelScope.launch {
        repository.insertDiary(entry)
    }

    suspend fun getAllDiaries(): List<DiaryEntry> = withContext(Dispatchers.IO) {
        repository.getAllDiaries()
    }
}
