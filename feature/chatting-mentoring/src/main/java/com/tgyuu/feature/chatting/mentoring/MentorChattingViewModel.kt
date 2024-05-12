package com.tgyuu.feature.chatting.mentoring

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.tgyuu.common.util.UiState
import com.tgyuu.model.consulting.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MentorChattingViewModel @Inject constructor() : ViewModel() {
    private val _chatText: MutableStateFlow<String> = MutableStateFlow("")
    val chatText get() = _chatText.asStateFlow()

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText get() = _searchText.asStateFlow()

    val chatLog: SnapshotStateList<Message> = mutableStateListOf()

    val _chatState: MutableStateFlow<UiState<Unit>> = MutableStateFlow(UiState.Success(Unit))
    val chatState = _chatState.asStateFlow()

    fun setChatText(chatText: String) {
        _chatText.value = chatText
    }

    fun setSearchText(searchText: String) {
        _searchText.value = searchText
    }
}
