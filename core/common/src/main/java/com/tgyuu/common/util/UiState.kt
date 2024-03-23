package com.tgyuu.common.util

sealed interface UiState<out T> {
    data object Init : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Success<out R>(val data: R) : UiState<R>
    data class Error(val message: String) : UiState<Nothing>
}
