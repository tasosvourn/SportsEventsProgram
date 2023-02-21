package com.example.domain.usecase

import com.example.domain.model.DomainResult
import com.example.domain.model.EmptyResponseErrorType
import com.example.domain.model.upcomingsportsevents.EventDomainModel
import com.example.domain.model.upcomingsportsevents.SportsDomainModel
import com.example.domain.repository.UpcomingSportsEventsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
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

    @Test
    fun getUpcomingSportsEvents_Successful() = runBlocking {
        upcomingSportsEventsRepository.stub {
            on { getUpcomingSportsEvents() } doReturn flow {
                emit(DomainResult.Success(getSuccessfulResponse()))
            }
        }

        val result = upcomingSportsEventsUseCase().first()
        assertTrue(result is DomainResult.Success)
        result as DomainResult.Success
        assertEquals(result.data[0].sportName, "example name 1")
        assertEquals(result.data[1].events[3].eventName, Pair("example first 3", "example second 3"))
        assertEquals(result.data[0].events[2].sportId, "example sport 1")
    }

    private fun getSuccessfulResponse(): List<SportsDomainModel> {
        val list = mutableListOf<SportsDomainModel>()
        list.add(getSportsDomainModelExample(1))
        list.add(getSportsDomainModelExample(2))
        return list
    }

    private fun getSportsDomainModelExample(i: Int): SportsDomainModel {
        return SportsDomainModel(
            sportName = "example name $i",
            sportId = "example sport $i",
            events = getEventsList(i)
        )
    }

    private fun getEventsList(i: Int): List<EventDomainModel> {
        val list = mutableListOf<EventDomainModel>()
        for(index in 0..3) {
            list.add(getEventDomainModelExample(index, i))
        }
        return list
    }

    private fun getEventDomainModelExample(index: Int, i: Int): EventDomainModel {
        return EventDomainModel(
            eventName = Pair("example first $index", "example second $index"),
            eventId = "exampleId $index",
            sportId = "example sport $i",
            sh = "example first $index - example second $index",
            eventStartTime = System.currentTimeMillis()
        )
    }
}