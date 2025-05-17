package com.example.moodpaws.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class AnimalFactResponse(
    val fact: String,
    val image: String
)

interface AnimalFactService {
    @GET("animal/{type}")
    suspend fun getAnimalFact(@Path("type") type: String): AnimalFactResponse
}

object AnimalFactApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://some-random-api.ml/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: AnimalFactService by lazy {
        retrofit.create(AnimalFactService::class.java)
    }
}
