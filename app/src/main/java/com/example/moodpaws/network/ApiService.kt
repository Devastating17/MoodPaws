package com.example.moodpaws.network

import com.example.moodpaws.data.Mood
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService{
    @GET("moods")
    suspend fun getMoods(): List<Mood>

    @POST("moods")
    suspend fun postMood(@Body mood: Mood): Response<Unit>
}