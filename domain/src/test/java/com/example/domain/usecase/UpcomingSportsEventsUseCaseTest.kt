package com.example.domain.usecase

import com.example.domain.model.DomainResult
import com.example.domain.model.EmptyResponseErrorType
import com.example.domain.repository.UpcomingSportsEventsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

class UpcomingSportsEventsUseCaseTest {
    private val upcomingSportsEventsRepository: UpcomingSportsEventsRepository = mock()
    private val upcomingSportsEventsUseCase = UpcomingSportsEventsUseCase(upcomingSportsEventsRepository)

    @Before
    fun setUp() {}

    @Test
    fun getUpcomingSportsEvents_emptyData() = runBlocking {
        upcomingSportsEventsRepository.stub {
            on { getUpcomingSportsEvents() } doReturn flow {
                emit(DomainResult.Success(listOf()))
            }
        }

        val result = upcomingSportsEventsUseCase().first()
        assertTrue(result is DomainResult.Error)
        result as DomainResult.Error
        assertTrue(result.errorDomainModel.errorType is EmptyResponseErrorType)
    }
}