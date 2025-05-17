package com.example.moodpaws.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moodpaws.data.Mood
import com.example.moodpaws.data.MoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class MoodViewModel(private val moodDao: MoodDao) : ViewModel() {

    val allMoods: LiveData<List<Mood>> = moodDao.getAllMoodsLive()

    fun insertMood(mood: Mood) {
        viewModelScope.launch(Dispatchers.IO) {
            moodDao.insertMood(mood)
        }
    }

    private val _filteredMoods = MutableLiveData<List<Mood>>()
    val filteredMoods: LiveData<List<Mood>> = _filteredMoods

    fun loadMoodsForTimeframe(timeframe: String) {
        viewModelScope.launch {
            val moods = withContext(Dispatchers.IO) {
                val now = LocalDate.now()
                when (timeframe) {
                    "week" -> {
                        val start = now.minusDays(6).toString()
                        moodDao.getMoodsSince(start)
                    }
                    "month" -> {
                        val month = String.format("%02d", now.monthValue)
                        val year = now.year.toString()
                        moodDao.getMoodsInMonth(month, year)
                    }
                    "year" -> {
                        val year = now.year.toString()
                        moodDao.getMoodsInYear(year)
                    }
                    else -> emptyList()
                }
            }
            _filteredMoods.value = moods
        }
    }
}
