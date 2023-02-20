package com.example.domain.model

/** Base class for the representation of an error. */
data class ErrorDomainModel(val errorType: DomainErrorType, val errorMessage: String? = null) {
    fun getAsDomainError(): DomainResult.Error = DomainResult.Error(this)
}

sealed class DomainErrorType

data class UnknownErrorType(val message: String  = "") : DomainErrorType()

data class HttpErrorType(
    val errorCode: Int? = null,
    val errorMessage: String?
): DomainErrorType()

data class NetworkExceptionType(
    val exception: Exception? = null,
    val errorMessage: String?
):  DomainErrorType()

object EmptyResponseErrorType : DomainErrorType()

