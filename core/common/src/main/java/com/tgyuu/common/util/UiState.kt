package com.tgyuu.common.util

sealed class UiState {
    data object Loading: UiState()
    data class Success<T>(val data: T): UiState()
    data class Error(val error: Throwable?): UiState() 
}