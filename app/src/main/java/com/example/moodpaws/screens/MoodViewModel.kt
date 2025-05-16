
package com.example.moodpaws.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodpaws.data.Mood
import com.example.moodpaws.data.MoodDatabase
import kotlinx.coroutines.launch

class MoodViewModel(application: Application) : AndroidViewModel(application) {
    private val moodDao = MoodDatabase.getDatabase(application).moodDao()

    fun insertMood(mood: Mood) {
        viewModelScope.launch {
            moodDao.insert(mood)
        }
    }
}
