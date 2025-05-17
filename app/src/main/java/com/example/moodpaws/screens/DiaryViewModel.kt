package com.example.moodpaws.screens

class DiaryViewModel(application: Application) : AndroidViewModel(application) {
    private val diaryDao = MoodDatabase.getDatabase(application).diaryDao()
    private val repo = DiaryRepository(diaryDao)

    fun insertDiary(entry: DiaryEntry) = viewModelScope.launch {
        repo.insertDiary(entry)
    }

    suspend fun getAllDiaries(): List<DiaryEntry> = repo.getAllDiaries()
}