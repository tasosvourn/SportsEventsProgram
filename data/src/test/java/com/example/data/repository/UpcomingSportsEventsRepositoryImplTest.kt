package com.example.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.networking.RestApiService
import com.example.domain.model.upcomingsportsevents.SportsDomainModel
import com.example.domain.repository.UpcomingSportsEventsRepository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import com.example.domain.model.DomainResult
import kotlinx.coroutines.flow.first
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class UpcomingSportsEventsRepositoryImplTest {

    private lateinit var repository: UpcomingSportsEventsRepository
    private lateinit var restApiService: RestApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val client = OkHttpClient.Builder().build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        restApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(RestApiService::class.java)

        repository = UpcomingSportsEventsRepositoryImpl(restApiService)
    }

    @Test
    fun testGetUpcomingSportsEvents_Success() = runBlocking {
        val expectedOutput = ClassLoader.getSystemResource("success_response.json").toString()

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(ClassLoader.getSystemResource("success_response.json").toString()))
        val response = repository.getUpcomingSportsEvents().first()

        assertTrue(response is DomainResult.Success)
        assertEquals(expectedOutput, response)
    }

    private fun processHttpErrorResponse(): Response<List<SportsDomainModel>> {
        return Response.error(HttpURLConnection.HTTP_UNAUTHORIZED,
            "HTTP_UNAUTHORIZED".toResponseBody("http://localhost".toMediaTypeOrNull()))
    }
}