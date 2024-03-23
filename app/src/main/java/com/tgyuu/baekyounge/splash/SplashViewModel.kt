package com.tgyuu.baekyounge.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _eventFlow = MutableSharedFlow<SplashEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _canSkipSplash = MutableStateFlow(false)
    val canSkipSplash = _canSkipSplash.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000L)
            _canSkipSplash.value = true
        }
    }

    fun event(event: SplashEvent) = viewModelScope.launch { _eventFlow.emit(event) }

    sealed class SplashEvent() {
        data object NavigateToHome : SplashEvent()
    }
}
