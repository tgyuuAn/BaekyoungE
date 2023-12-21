package com.tgyuu.common.util

sealed class UiState<out T> {
    data object Nothing : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val error: Throwable?) : UiState<Nothing>()
}