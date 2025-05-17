package com.example.moodpaws.data

class DiaryRepository(private val dao: DiaryDao) {
    suspend fun insertDiary(entry: DiaryEntry) = dao.insertDiary(entry)
    suspend fun getAllDiaries(): List<DiaryEntry> = dao.getAllDiaries()
}
