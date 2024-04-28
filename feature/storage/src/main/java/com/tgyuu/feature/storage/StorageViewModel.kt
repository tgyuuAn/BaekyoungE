package com.tgyuu.feature.storage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor() : ViewModel() {
    private val _yearList = MutableStateFlow(listOf("2024"))
    val yearList = _yearList.asStateFlow()

    private val _selectedYear = MutableStateFlow("2024")
    val selectedYear = _selectedYear.asStateFlow()

    fun setSelectedYear(year: String){
        _selectedYear.value = year
    }
}
