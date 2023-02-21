package com.example.data.networking

import com.example.data.model.upcomingsportevents.response.Sports
import retrofit2.Response
import retrofit2.http.GET

interface RestApiService {

    @GET("sports/")
    suspend fun getUpcomingSportsEvents(): Response<List<Sports>>
}