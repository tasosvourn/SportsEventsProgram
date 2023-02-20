package com.example.data.repository

import com.example.data.mapper.mapToDomain
import com.example.data.networking.RestApiService
import com.example.domain.model.*
import com.example.domain.model.upcomingsportsevents.SportsDomainModel
import com.example.domain.repository.UpcomingSportsEventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpcomingSportsEventsRepositoryImpl(
    private val restApiService: RestApiService,
) : UpcomingSportsEventsRepository {
    override fun getUpcomingSportsEvents(): Flow<DomainResult<List<SportsDomainModel>>> = flow {
        val response = restApiService.getUpcomingSportsEvents()
        try {
            if (response.isSuccessful) {
                emit(DomainResult.Success(response.body()?.map { it.mapToDomain() } ?: listOf()))
            } else DomainResult.Error(ErrorDomainModel(HttpErrorType(response.code(),
                response.message())))
        } catch (exception: Exception) {
            val message = exception.message ?: ""
            DomainResult.Error(ErrorDomainModel(NetworkExceptionType(exception,
                message.ifEmpty { exception.javaClass.name })))
        }
    }
}