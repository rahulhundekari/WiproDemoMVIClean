package com.demo.wiprodemo.domain.utils

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: Throwable) : Result<T>()
    data object Loading : Result<Nothing>()
}