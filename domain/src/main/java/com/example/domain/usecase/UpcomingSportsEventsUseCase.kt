package com.example.domain.usecase

import com.example.domain.model.DomainResult
import com.example.domain.model.EmptyResponseErrorType
import com.example.domain.model.ErrorDomainModel
import com.example.domain.model.upcomingsportsevents.SportsDomainModel
import com.example.domain.repository.UpcomingSportsEventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/** Use case that makes the API call and passes the result Domain Model to the viewModel */
class UpcomingSportsEventsUseCase @Inject constructor(private val upcomingSportsEventsRepository: UpcomingSportsEventsRepository) {
    operator fun invoke(): Flow<DomainResult<List<SportsDomainModel>>> = flow {
        when (val result = upcomingSportsEventsRepository.getUpcomingSportsEvents().first()) {
            is DomainResult.Error -> emit(result)
            is DomainResult.Success -> {
                if (result.data.isNotEmpty()) emit(result)
                else emit(DomainResult.Error(ErrorDomainModel(EmptyResponseErrorType)))
            }
        }
    }
}